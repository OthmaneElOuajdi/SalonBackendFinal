package com.salon.coiffure.service;

import java.util.List;

import com.salon.coiffure.entity.Notification;
import com.salon.coiffure.entity.NotificationStatus;
import com.salon.coiffure.service.base.BaseService;

/**
 * Interface du service pour la gestion des notifications.
 */
public interface NotificationService extends BaseService<Notification, Long> {

    /**
     * Recherche les notifications d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @return Liste des notifications de l'utilisateur
     */
    List<Notification> findByUser(Long userId);

    /**
     * Marque une notification comme lue.
     *
     * @param id L'ID de la notification
     * @return La notification mise à jour
     */
    Notification markAsRead(Long id);

    /**
     * Met à jour le statut d'une notification.
     *
     * @param id L'ID de la notification
     * @param status Le nouveau statut
     * @return La notification mise à jour
     */
    Notification updateStatus(Long id, NotificationStatus status);
}
