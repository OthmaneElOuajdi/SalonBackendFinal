package com.salon.coiffure.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.ERole;
import com.salon.coiffure.entity.Role;
import com.salon.coiffure.repository.RoleRepository;
import com.salon.coiffure.service.RoleService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des rôles.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * Constructeur pour l'injection du repository des rôles.
     *
     * @param repository Le repository pour les entités Role
     */
    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
        this.roleRepository = repository;
    }

    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

}
