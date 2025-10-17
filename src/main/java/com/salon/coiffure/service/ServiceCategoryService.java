package com.salon.coiffure.service;

import java.util.List;

import com.salon.coiffure.entity.ServiceCategory;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des catégories de services.
 */
public interface ServiceCategoryService extends BaseService<ServiceCategory, Long> {

    /**
     * Recherche les catégories actives triées par ordre d'affichage.
     *
     * @return Liste des catégories actives
     */
    List<ServiceCategory> findAllActiveOrdered();

    /**
     * Recherche les catégories par statut actif.
     *
     * @param active Statut actif
     * @return Liste des catégories
     */
    List<ServiceCategory> findByActive(boolean active);
}
