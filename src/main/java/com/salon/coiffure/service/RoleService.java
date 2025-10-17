package com.salon.coiffure.service;

import java.util.Optional;

import com.salon.coiffure.entity.ERole;
import com.salon.coiffure.entity.Role;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des rôles.
 */
public interface RoleService extends BaseService<Role, Long> {

    /**
     * Recherche un rôle par son nom.
     *
     * @param name Le nom du rôle (ERole)
     * @return Un Optional contenant le rôle si trouvé
     */
    Optional<Role> findByName(ERole name);
}
