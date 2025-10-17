package com.salon.coiffure.service;

import com.salon.coiffure.dto.payment.PaymentRequestDTO;
import com.salon.coiffure.dto.payment.PaymentResponseDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;

/**
 * Interface du service pour l'intégration Stripe.
 */
public interface StripeService {

    /**
     * Crée un PaymentIntent Stripe pour un rendez-vous.
     *
     * @param request Les détails de la demande de paiement
     * @return La réponse contenant les informations du PaymentIntent
     * @throws StripeException Si une erreur Stripe se produit
     */
    PaymentResponseDTO createPaymentIntent(PaymentRequestDTO request) throws StripeException;

    /**
     * Confirme un PaymentIntent existant.
     *
     * @param paymentIntentId L'ID du PaymentIntent à confirmer
     * @return Le PaymentIntent confirmé
     * @throws StripeException Si une erreur Stripe se produit
     */
    PaymentIntent confirmPaymentIntent(String paymentIntentId) throws StripeException;

    /**
     * Annule un PaymentIntent.
     *
     * @param paymentIntentId L'ID du PaymentIntent à annuler
     * @return Le PaymentIntent annulé
     * @throws StripeException Si une erreur Stripe se produit
     */
    PaymentIntent cancelPaymentIntent(String paymentIntentId) throws StripeException;

    /**
     * Récupère un PaymentIntent par son ID.
     *
     * @param paymentIntentId L'ID du PaymentIntent
     * @return Le PaymentIntent
     * @throws StripeException Si une erreur Stripe se produit
     */
    PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException;

    /**
     * Crée un remboursement pour un paiement.
     *
     * @param paymentIntentId L'ID du PaymentIntent à rembourser
     * @return Le remboursement créé
     * @throws StripeException Si une erreur Stripe se produit
     */
    Refund createRefund(String paymentIntentId) throws StripeException;

    /**
     * Traite un événement webhook Stripe.
     *
     * @param payload Le corps de la requête webhook
     * @param sigHeader La signature Stripe
     * @return true si l'événement a été traité avec succès
     */
    boolean handleWebhookEvent(String payload, String sigHeader);
}
