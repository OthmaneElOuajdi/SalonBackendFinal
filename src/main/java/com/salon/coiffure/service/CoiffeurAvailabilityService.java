package com.salon.coiffure.service;

import java.time.LocalDateTime;
import java.util.List;

import com.salon.coiffure.entity.CoiffeurAvailability;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des disponibilités des coiffeurs.
 */
public interface CoiffeurAvailabilityService extends BaseService<CoiffeurAvailability, Long> {

    /**
     * Recherche les disponibilités d'un coiffeur.
     *
     * @param coiffeurId L'ID du coiffeur
     * @return Liste des disponibilités du coiffeur
     */
    List<CoiffeurAvailability> findByCoiffeur(Long coiffeurId);

    /**
     * Recherche les disponibilités d'un coiffeur entre deux dates.
     *
     * @param coiffeurId L'ID du coiffeur
     * @param start Date de début
     * @param end Date de fin
     * @return Liste des disponibilités dans la période
     */
    List<CoiffeurAvailability> findByCoiffeurBetween(Long coiffeurId, LocalDateTime start, LocalDateTime end);

    /**
     * Vérifie si un coiffeur est indisponible dans une période.
     *
     * @param coiffeurId L'ID du coiffeur
     * @param start Date de début
     * @param end Date de fin
     * @return true si le coiffeur est indisponible, false sinon
     */
    boolean isCoiffeurUnavailable(Long coiffeurId, LocalDateTime start, LocalDateTime end);
}
