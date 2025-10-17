package com.salon.coiffure.service;

import java.time.LocalDateTime;
import java.util.List;

import com.salon.coiffure.entity.BlockedSlot;

/**
 * Interface du service pour la gestion des créneaux horaires bloqués.
 */
public interface BlockedSlotService {

    /**
     * Crée un nouveau créneau bloqué.
     *
     * @param slot Le créneau à créer
     * @return Le créneau créé
     */
    BlockedSlot create(BlockedSlot slot);

    /**
     * Supprime un créneau bloqué par son ID.
     *
     * @param id L'ID du créneau
     */
    void deleteById(Long id);

    /**
     * Recherche les créneaux bloqués entre deux dates.
     *
     * @param start Date de début
     * @param end Date de fin
     * @return Liste des créneaux bloqués dans la période
     */
    List<BlockedSlot> findBetween(LocalDateTime start, LocalDateTime end);
}
