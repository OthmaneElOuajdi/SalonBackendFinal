package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.notification.NotificationCreateDTO;
import com.salon.coiffure.dto.notification.NotificationResponseDTO;
import com.salon.coiffure.entity.Notification;
import com.salon.coiffure.entity.User;

/**
 * Mapper responsable de la conversion entre les entités {@link Notification}
 * et les différents DTOs de notification.
 */
@Component
public class NotificationMapper {

    /**
     * Convertit une entité {@link Notification} en {@link NotificationResponseDTO}.
     *
     * @param notification l'entité {@link Notification} à convertir
     * @return une instance de {@link NotificationResponseDTO}
     */
    public NotificationResponseDTO toResponseDTO(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .type(notification.getType())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .sentAt(notification.getSentAt())
                .readAt(notification.getReadAt())
                .userId(notification.getUser() != null ? notification.getUser().getId() : null)
                .userName(notification.getUser() != null ? notification.getUser().getFullName() : null)
                .build();
    }

    /**
     * Convertit une liste d'entités {@link Notification} en liste de {@link NotificationResponseDTO}.
     *
     * @param notifications la liste d'entités {@link Notification} à convertir
     * @return une liste de {@link NotificationResponseDTO}
     */
    public List<NotificationResponseDTO> toResponseDTOList(List<Notification> notifications) {
        if (notifications == null) {
            return null;
        }

        return notifications.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link NotificationCreateDTO} en entité {@link Notification}.
     *
     * @param dto le {@link NotificationCreateDTO} à convertir
     * @param user l'utilisateur destinataire de la notification
     * @return une nouvelle entité {@link Notification}
     */
    public Notification toEntity(NotificationCreateDTO dto, User user) {
        if (dto == null) {
            return null;
        }

        return Notification.builder()
                .user(user)
                .type(dto.getType())
                .message(dto.getMessage()) // L'entité n'a pas de title, on utilise message
                .build();
    }
}
