package com.salon.coiffure.dto.loyalty;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour les points de fidélité d'un utilisateur.
 */
@Schema(description = "Points de fidélité d'un utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyPointDTO {

    @Schema(description = "Identifiant de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Variation de points (peut être négatif)", example = "50")
    private int deltaPoints;

    @Schema(description = "Total actuel de points", example = "250")
    private int totalPoints;

    @Schema(description = "Raison de l'ajout/retrait", example = "Rendez-vous complété")
    private String reason;

    @Schema(description = "Date de l'opération", example = "2025-10-17T12:00:00")
    private LocalDateTime createdAt;
}
