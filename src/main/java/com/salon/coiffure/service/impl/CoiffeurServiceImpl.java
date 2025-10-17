package com.salon.coiffure.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.Coiffeur;
import com.salon.coiffure.repository.CoiffeurRepository;
import com.salon.coiffure.service.CoiffeurService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des coiffeurs.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class CoiffeurServiceImpl extends BaseServiceImpl<Coiffeur, Long> implements CoiffeurService {

    private final CoiffeurRepository coiffeurRepository;

    /**
     * Constructeur pour l'injection du repository des coiffeurs.
     *
     * @param repository Le repository pour les entités Coiffeur
     */
    public CoiffeurServiceImpl(CoiffeurRepository repository) {
        super(repository);
        this.coiffeurRepository = repository;
    }

    @Override
    public Optional<Coiffeur> findByUserId(Long userId) {
        return coiffeurRepository.findByUserId(userId);
    }

    @Override
    public List<Coiffeur> findByActive(boolean active) {
        return coiffeurRepository.findByActive(active);
    }

    @Override
    public List<Coiffeur> findAllActive() {
        return coiffeurRepository.findByActiveOrderByProfessionalNameAsc(true);
    }

    @Override
    public List<Coiffeur> findActive() {
        return coiffeurRepository.findByActive(true);
    }
}
