package com.salon.coiffure.dto.notification;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour une notification envoyée à un utilisateur.
 */
@Schema(description = "Notification envoyée à un utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    @Schema(description = "Identifiant de la notification", example = "1")
    private Long id;

    @Schema(description = "Identifiant de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Message de la notification", example = "Votre rendez-vous a été confirmé")
    private String message;

    @Schema(description = "Type de notification", example = "BOOKING")
    private String type;

    @Schema(description = "Statut de la notification", example = "UNREAD")
    private String status;

    @Schema(description = "Date d'envoi de la notification", example = "2025-10-17T12:00:00")
    private LocalDateTime sentAt;

    @Schema(description = "Date de lecture de la notification", example = "2025-10-17T12:30:00")
    private LocalDateTime readAt;
}
