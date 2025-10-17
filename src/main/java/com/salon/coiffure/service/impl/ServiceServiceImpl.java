package com.salon.coiffure.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.repository.ServiceRepository;
import com.salon.coiffure.service.ServiceService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des services offerts par le salon.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class ServiceServiceImpl extends BaseServiceImpl<com.salon.coiffure.entity.Service, Long> implements ServiceService {

    /**
     * Constructeur pour l'injection du repository des services.
     *
     * @param repository Le repository pour les entités Service
     */
    public ServiceServiceImpl(ServiceRepository repository) {
        super(repository);
    }

}
