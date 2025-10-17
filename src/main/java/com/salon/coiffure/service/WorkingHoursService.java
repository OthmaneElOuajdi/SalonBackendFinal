package com.salon.coiffure.service;

import java.time.DayOfWeek;
import java.util.Optional;

import com.salon.coiffure.entity.WorkingHours;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des horaires de travail.
 */
public interface WorkingHoursService extends BaseService<WorkingHours, Long> {

    /**
     * Sauvegarde ou met à jour les horaires de travail.
     *
     * @param wh Les horaires de travail
     * @return Les horaires sauvegardés
     */
    WorkingHours save(WorkingHours wh);

    /**
     * Recherche les horaires par jour de la semaine.
     *
     * @param day Le jour de la semaine
     * @return Un Optional contenant les horaires si trouvés
     */
    Optional<WorkingHours> findByDay(DayOfWeek day);

    /**
     * Recherche les horaires par jour de la semaine.
     *
     * @param dayOfWeek Le jour de la semaine
     * @return Un Optional contenant les horaires si trouvés
     */
    Optional<WorkingHours> findByDayOfWeek(DayOfWeek dayOfWeek);

    /**
     * Vérifie si le salon est ouvert un jour donné.
     *
     * @param dayOfWeek Le jour de la semaine
     * @return true si ouvert, false sinon
     */
    boolean isOpen(DayOfWeek dayOfWeek);
}
