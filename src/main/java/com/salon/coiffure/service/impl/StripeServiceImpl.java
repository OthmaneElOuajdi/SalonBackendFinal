package com.salon.coiffure.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.dto.payment.PaymentRequestDTO;
import com.salon.coiffure.dto.payment.PaymentResponseDTO;
import com.salon.coiffure.entity.Payment;
import com.salon.coiffure.entity.PaymentStatus;
import com.salon.coiffure.entity.RendezVous;
import com.salon.coiffure.repository.PaymentRepository;
import com.salon.coiffure.repository.RendezVousRepository;
import com.salon.coiffure.service.StripeService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCancelParams;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implémentation du service Stripe pour gérer les paiements.
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StripeServiceImpl implements StripeService {

    private final PaymentRepository paymentRepository;
    private final RendezVousRepository rendezVousRepository;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @Override
    public PaymentResponseDTO createPaymentIntent(PaymentRequestDTO request) throws StripeException {
        log.info("Création d'un PaymentIntent pour le rendez-vous ID: {}", request.getRendezVousId());

        RendezVous rendezVous = rendezVousRepository.findById(request.getRendezVousId())
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID: " + request.getRendezVousId()));

        long amountInCents = request.getAmount().multiply(new BigDecimal("100")).longValue();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(request.getCurrency().toLowerCase())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .putMetadata("rendezVousId", request.getRendezVousId().toString())
                .putMetadata("userId", rendezVous.getUser().getId().toString())
                .setDescription("Paiement pour rendez-vous #" + request.getRendezVousId())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        Payment payment = Payment.builder()
                .user(rendezVous.getUser())
                .rendezVous(rendezVous)
                .amount(request.getAmount().doubleValue())
                .status(PaymentStatus.PENDING)
                .paymentMethod("stripe")
                .transactionId(paymentIntent.getId())
                .message("PaymentIntent créé")
                .paymentDate(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        log.info("PaymentIntent créé avec succès: {}", paymentIntent.getId());

        return PaymentResponseDTO.builder()
                .paymentIntentId(paymentIntent.getId())
                .clientSecret(paymentIntent.getClientSecret())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .status(paymentIntent.getStatus())
                .build();
    }

    @Override
    public PaymentIntent confirmPaymentIntent(String paymentIntentId) throws StripeException {
        log.info("Confirmation du PaymentIntent: {}", paymentIntentId);

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        PaymentIntentConfirmParams params = PaymentIntentConfirmParams.builder().build();

        PaymentIntent confirmedIntent = paymentIntent.confirm(params);

        updatePaymentStatus(paymentIntentId, PaymentStatus.COMPLETED, "Paiement confirmé");

        log.info("PaymentIntent confirmé avec succès: {}", paymentIntentId);

        return confirmedIntent;
    }

    @Override
    public PaymentIntent cancelPaymentIntent(String paymentIntentId) throws StripeException {
        log.info("Annulation du PaymentIntent: {}", paymentIntentId);

        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

        PaymentIntentCancelParams params = PaymentIntentCancelParams.builder().build();

        PaymentIntent cancelledIntent = paymentIntent.cancel(params);

        updatePaymentStatus(paymentIntentId, PaymentStatus.CANCELLED, "Paiement annulé");

        log.info("PaymentIntent annulé avec succès: {}", paymentIntentId);

        return cancelledIntent;
    }

    @Override
    public PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
        log.info("Récupération du PaymentIntent: {}", paymentIntentId);
        return PaymentIntent.retrieve(paymentIntentId);
    }

    @Override
    public Refund createRefund(String paymentIntentId) throws StripeException {
        log.info("Création d'un remboursement pour le PaymentIntent: {}", paymentIntentId);

        RefundCreateParams params = RefundCreateParams.builder()
                .setPaymentIntent(paymentIntentId)
                .build();

        Refund refund = Refund.create(params);

        updatePaymentStatus(paymentIntentId, PaymentStatus.REFUNDED, "Paiement remboursé");

        log.info("Remboursement créé avec succès: {}", refund.getId());

        return refund;
    }

    @Override
    public boolean handleWebhookEvent(String payload, String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (SignatureVerificationException e) {
            log.error("Échec de la vérification de la signature du webhook: {}", e.getMessage());
            return false;
        }

        log.info("Événement webhook reçu: {}", event.getType());

        switch (event.getType()) {
            case "payment_intent.succeeded":
                handlePaymentIntentSucceeded(event);
                break;
            case "payment_intent.payment_failed":
                handlePaymentIntentFailed(event);
                break;
            case "payment_intent.canceled":
                handlePaymentIntentCanceled(event);
                break;
            case "charge.refunded":
                handleChargeRefunded(event);
                break;
            default:
                log.info("Type d'événement non géré: {}", event.getType());
        }

        return true;
    }

    /**
     * Gère l'événement de succès d'un PaymentIntent.
     */
    private void handlePaymentIntentSucceeded(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject()
                .orElse(null);

        if (paymentIntent != null) {
            log.info("Paiement réussi pour PaymentIntent: {}", paymentIntent.getId());
            updatePaymentStatus(paymentIntent.getId(), PaymentStatus.COMPLETED, "Paiement réussi");
        }
    }

    /**
     * Gère l'événement d'échec d'un PaymentIntent.
     */
    private void handlePaymentIntentFailed(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject()
                .orElse(null);

        if (paymentIntent != null) {
            log.warn("Échec du paiement pour PaymentIntent: {}", paymentIntent.getId());
            updatePaymentStatus(paymentIntent.getId(), PaymentStatus.FAILED, "Échec du paiement");
        }
    }

    /**
     * Gère l'événement d'annulation d'un PaymentIntent.
     */
    private void handlePaymentIntentCanceled(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject()
                .orElse(null);

        if (paymentIntent != null) {
            log.info("Paiement annulé pour PaymentIntent: {}", paymentIntent.getId());
            updatePaymentStatus(paymentIntent.getId(), PaymentStatus.CANCELLED, "Paiement annulé");
        }
    }

    /**
     * Gère l'événement de remboursement d'un charge.
     */
    private void handleChargeRefunded(Event event) {
        log.info("Remboursement traité via webhook");
        // La logique de remboursement est déjà gérée dans createRefund()
    }

    /**
     * Met à jour le statut d'un paiement dans la base de données.
     */
    private void updatePaymentStatus(String transactionId, PaymentStatus status, String message) {
        paymentRepository.findByTransactionId(transactionId).ifPresent(payment -> {
            payment.setStatus(status);
            payment.setMessage(message);
            paymentRepository.save(payment);
            log.info("Statut du paiement mis à jour: {} -> {}", transactionId, status);
        });
    }
}
