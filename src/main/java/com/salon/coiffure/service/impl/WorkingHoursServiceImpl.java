package com.salon.coiffure.service.impl;

import java.time.DayOfWeek;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.WorkingHours;
import com.salon.coiffure.repository.WorkingHoursRepository;
import com.salon.coiffure.service.WorkingHoursService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des horaires de travail.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class WorkingHoursServiceImpl extends BaseServiceImpl<WorkingHours, Long> implements WorkingHoursService {

    private final WorkingHoursRepository workingHoursRepository;

    /**
     * Constructeur pour l'injection du repository des horaires de travail.
     *
     * @param repository Le repository pour les entités WorkingHours
     */
    public WorkingHoursServiceImpl(WorkingHoursRepository repository) {
        super(repository);
        this.workingHoursRepository = repository;
    }

    @Override
    public WorkingHours save(WorkingHours wh) {
        return create(wh);
    }

    @Override
    public Optional<WorkingHours> findByDay(DayOfWeek day) {
        return workingHoursRepository.findByDayOfWeek(day);
    }

    @Override
    public Optional<WorkingHours> findByDayOfWeek(DayOfWeek dayOfWeek) {
        return workingHoursRepository.findByDayOfWeek(dayOfWeek);
    }

    @Override
    public boolean isOpen(DayOfWeek dayOfWeek) {
        return workingHoursRepository.findByDayOfWeek(dayOfWeek)
                .map(wh -> !wh.isClosed())
                .orElse(false);
    }

}
