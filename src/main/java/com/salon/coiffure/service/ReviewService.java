package com.salon.coiffure.service;

import java.util.List;

import com.salon.coiffure.entity.Review;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des avis clients.
 */
public interface ReviewService extends BaseService<Review, Long> {

    /**
     * Recherche les avis d'un coiffeur.
     *
     * @param coiffeurId L'ID du coiffeur
     * @return Liste des avis du coiffeur
     */
    List<Review> findByCoiffeur(Long coiffeurId);

    /**
     * Recherche les avis approuvés d'un coiffeur.
     *
     * @param coiffeurId L'ID du coiffeur
     * @return Liste des avis approuvés
     */
    List<Review> findApprovedByCoiffeur(Long coiffeurId);

    /**
     * Recherche les avis d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @return Liste des avis de l'utilisateur
     */
    List<Review> findByUser(Long userId);

    /**
     * Recherche les avis en attente d'approbation.
     *
     * @return Liste des avis en attente
     */
    List<Review> findPendingReviews();

    /**
     * Approuve un avis.
     *
     * @param id L'ID de l'avis
     * @return L'avis approuvé
     */
    Review approveReview(Long id);

    /**
     * Calcule la note moyenne d'un coiffeur.
     *
     * @param coiffeurId L'ID du coiffeur
     * @return La note moyenne
     */
    Double getAverageRating(Long coiffeurId);
}
