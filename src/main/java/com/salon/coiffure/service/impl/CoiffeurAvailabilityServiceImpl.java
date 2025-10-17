package com.salon.coiffure.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.AvailabilityType;
import com.salon.coiffure.entity.CoiffeurAvailability;
import com.salon.coiffure.repository.CoiffeurAvailabilityRepository;
import com.salon.coiffure.service.CoiffeurAvailabilityService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des disponibilités des coiffeurs.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class CoiffeurAvailabilityServiceImpl extends BaseServiceImpl<CoiffeurAvailability, Long> 
        implements CoiffeurAvailabilityService {

    private final CoiffeurAvailabilityRepository availabilityRepository;

    /**
     * Constructeur pour l'injection du repository des disponibilités.
     *
     * @param repository Le repository pour les entités CoiffeurAvailability
     */
    public CoiffeurAvailabilityServiceImpl(CoiffeurAvailabilityRepository repository) {
        super(repository);
        this.availabilityRepository = repository;
    }

    @Override
    public List<CoiffeurAvailability> findByCoiffeur(Long coiffeurId) {
        return availabilityRepository.findByCoiffeurIdOrderByStartAtAsc(coiffeurId);
    }

    @Override
    public List<CoiffeurAvailability> findByCoiffeurBetween(Long coiffeurId, LocalDateTime start, LocalDateTime end) {
        return availabilityRepository.findByCoiffeurIdAndStartAtLessThanEqualAndEndAtGreaterThanEqual(
                coiffeurId, start, end);
    }

    @Override
    public boolean isCoiffeurUnavailable(Long coiffeurId, LocalDateTime start, LocalDateTime end) {
        return availabilityRepository.isCoiffeurUnavailable(coiffeurId, start, end, AvailabilityType.UNAVAILABLE);
    }
}
