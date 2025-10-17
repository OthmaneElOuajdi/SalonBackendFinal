package com.salon.coiffure.dto.notification;

import com.salon.coiffure.entity.NotificationType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer une nouvelle notification.
 */
@Schema(description = "Données pour créer une nouvelle notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationCreateDTO {

    @Schema(description = "ID de l'utilisateur destinataire", example = "1")
    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    @Positive(message = "L'ID de l'utilisateur doit être positif")
    private Long userId;

    @Schema(description = "Type de notification", example = "BOOKING")
    @NotNull(message = "Le type de notification est obligatoire")
    private NotificationType type;

    @Schema(description = "Message de la notification", example = "Votre rendez-vous a été confirmé pour demain à 14h00")
    @NotBlank(message = "Le message est obligatoire")
    @Size(max = 1000, message = "Le message ne peut pas dépasser 1000 caractères")
    private String message;
}
