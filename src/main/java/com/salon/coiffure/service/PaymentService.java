package com.salon.coiffure.service;

import java.util.List;
import java.util.Optional;

import com.salon.coiffure.entity.Payment;
import com.salon.coiffure.entity.PaymentStatus;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des paiements.
 */
public interface PaymentService extends BaseService<Payment, Long> {

    /**
     * Recherche les paiements par statut.
     *
     * @param status Le statut du paiement
     * @return Liste des paiements avec ce statut
     */
    List<Payment> findByStatus(PaymentStatus status);

    /**
     * Recherche un paiement par rendez-vous.
     *
     * @param rendezVousId L'ID du rendez-vous
     * @return Un Optional contenant le paiement si trouvé
     */
    Optional<Payment> findByRendezVous(Long rendezVousId);

    /**
     * Met à jour le statut d'un paiement.
     *
     * @param paymentId L'ID du paiement
     * @param status Le nouveau statut
     * @param message Message associé à la mise à jour
     * @return Le paiement mis à jour
     */
    Payment updateStatus(Long paymentId, PaymentStatus status, String message);
}
