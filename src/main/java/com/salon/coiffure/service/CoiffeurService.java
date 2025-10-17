package com.salon.coiffure.service;

import java.util.List;
import java.util.Optional;

import com.salon.coiffure.entity.Coiffeur;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des coiffeurs.
 */
public interface CoiffeurService extends BaseService<Coiffeur, Long> {

    /**
     * Recherche un coiffeur par son utilisateur associé.
     *
     * @param userId L'ID de l'utilisateur
     * @return Un Optional contenant le coiffeur si trouvé
     */
    Optional<Coiffeur> findByUserId(Long userId);

    /**
     * Recherche les coiffeurs actifs.
     *
     * @param active Statut actif
     * @return Liste des coiffeurs actifs
     */
    List<Coiffeur> findByActive(boolean active);

    /**
     * Recherche tous les coiffeurs actifs triés par nom.
     *
     * @return Liste des coiffeurs actifs triés
     */
    List<Coiffeur> findAllActive();

    /**
     * Recherche les coiffeurs actifs uniquement.
     *
     * @return Liste des coiffeurs actifs
     */
    List<Coiffeur> findActive();
}
