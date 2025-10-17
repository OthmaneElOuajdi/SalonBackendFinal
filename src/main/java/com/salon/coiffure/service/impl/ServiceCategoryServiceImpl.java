package com.salon.coiffure.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.ServiceCategory;
import com.salon.coiffure.repository.ServiceCategoryRepository;
import com.salon.coiffure.service.ServiceCategoryService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des catégories de services.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class ServiceCategoryServiceImpl extends BaseServiceImpl<ServiceCategory, Long> 
        implements ServiceCategoryService {

    private final ServiceCategoryRepository categoryRepository;

    /**
     * Constructeur pour l'injection du repository des catégories.
     *
     * @param repository Le repository pour les entités ServiceCategory
     */
    public ServiceCategoryServiceImpl(ServiceCategoryRepository repository) {
        super(repository);
        this.categoryRepository = repository;
    }

    @Override
    public List<ServiceCategory> findAllActiveOrdered() {
        return categoryRepository.findByActiveOrderByDisplayOrderAsc(true);
    }

    @Override
    public List<ServiceCategory> findByActive(boolean active) {
        return categoryRepository.findByActiveOrderByDisplayOrderAsc(active);
    }
}
