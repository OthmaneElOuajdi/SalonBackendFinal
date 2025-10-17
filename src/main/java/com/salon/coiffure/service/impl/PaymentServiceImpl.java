package com.salon.coiffure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.Payment;
import com.salon.coiffure.entity.PaymentStatus;
import com.salon.coiffure.repository.PaymentRepository;
import com.salon.coiffure.service.PaymentService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des paiements.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class PaymentServiceImpl extends BaseServiceImpl<Payment, Long> implements PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * Constructeur pour l'injection du repository des paiements.
     *
     * @param repository Le repository pour les entités Payment
     */
    public PaymentServiceImpl(PaymentRepository repository) {
        super(repository);
        this.paymentRepository = repository;
    }

    @Override
    public List<Payment> findByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @Override
    public Optional<Payment> findByRendezVous(Long rendezVousId) {
        return paymentRepository.findByRendezVousId(rendezVousId);
    }

    @Override
    public Payment updateStatus(Long paymentId, PaymentStatus status, String message) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec l'ID: " + paymentId));
        
        payment.setStatus(status);
        payment.setMessage(message);
        
        return paymentRepository.save(payment);
    }
}
