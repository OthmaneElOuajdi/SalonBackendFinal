package com.salon.coiffure.dto.notification;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salon.coiffure.entity.NotificationStatus;
import com.salon.coiffure.entity.NotificationType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse d'une notification avec informations complètes.
 */
@Schema(description = "Données de réponse d'une notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {

    @Schema(description = "Identifiant unique de la notification", example = "1")
    private Long id;

    @Schema(description = "ID de l'utilisateur destinataire", example = "1")
    private Long userId;

    @Schema(description = "Nom complet de l'utilisateur destinataire", example = "Jean Dupont")
    private String userName;

    @Schema(description = "Type de notification", example = "BOOKING")
    private NotificationType type;

    @Schema(description = "Message de la notification", example = "Votre rendez-vous a été confirmé")
    private String message;

    @Schema(description = "Statut de la notification", example = "UNREAD")
    private NotificationStatus status;

    @Schema(description = "Date d'envoi", example = "17/10/2025 12:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime sentAt;

    @Schema(description = "Date de lecture", example = "17/10/2025 12:30:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime readAt;
}
