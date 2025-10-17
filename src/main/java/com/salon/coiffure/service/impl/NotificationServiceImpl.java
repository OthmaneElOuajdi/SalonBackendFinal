package com.salon.coiffure.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.Notification;
import com.salon.coiffure.entity.NotificationStatus;
import com.salon.coiffure.repository.NotificationRepository;
import com.salon.coiffure.service.NotificationService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des notifications.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class NotificationServiceImpl extends BaseServiceImpl<Notification, Long> implements NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Constructeur pour l'injection du repository des notifications.
     *
     * @param repository Le repository pour les entités Notification
     */
    public NotificationServiceImpl(NotificationRepository repository) {
        super(repository);
        this.notificationRepository = repository;
    }

    @Override
    public List<Notification> findByUser(Long userId) {
        return notificationRepository.findByUserIdOrderBySentAtDesc(userId);
    }

    @Override
    public Notification markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification introuvable avec l'ID: " + id));
        
        notification.setStatus(NotificationStatus.READ);
        notification.setReadAt(LocalDateTime.now());
        
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateStatus(Long id, NotificationStatus status) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification introuvable avec l'ID: " + id));
        
        notification.setStatus(status);
        
        return notificationRepository.save(notification);
    }
}
