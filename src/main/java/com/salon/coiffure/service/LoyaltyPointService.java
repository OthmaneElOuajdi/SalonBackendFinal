package com.salon.coiffure.service;

import java.util.List;

import com.salon.coiffure.entity.LoyaltyPointTransaction;

/**
 * Interface du service de gestion des points de fidélité.
 * Gère l'attribution, l'utilisation et la consultation des points.
 */
public interface LoyaltyPointService {

    /**
     * Attribue des points de fidélité à un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @param points Le nombre de points à attribuer
     * @param reason La raison de l'attribution
     * @return La transaction créée
     */
    LoyaltyPointTransaction awardPoints(Long userId, int points, String reason);

    /**
     * Utilise/retire des points de fidélité d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @param points Le nombre de points à utiliser
     * @param reason La raison de l'utilisation
     * @return La transaction créée
     */
    LoyaltyPointTransaction redeemPoints(Long userId, int points, String reason);

    /**
     * Récupère le total des points d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @return Le total des points
     */
    int getTotalPoints(Long userId);

    /**
     * Récupère l'historique des transactions de points d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @return Liste des transactions
     */
    List<LoyaltyPointTransaction> history(Long userId);
}
