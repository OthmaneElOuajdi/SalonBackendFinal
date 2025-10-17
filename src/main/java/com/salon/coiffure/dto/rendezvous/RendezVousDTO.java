package com.salon.coiffure.dto.rendezvous;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour les détails d'un rendez-vous.
 */
@Schema(description = "Détails d'un rendez-vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVousDTO {

    @Schema(description = "Identifiant du rendez-vous", example = "1")
    private Long id;

    @Schema(description = "Identifiant de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Nom complet de l'utilisateur", example = "Jean Dupont")
    private String userName;

    @Schema(description = "Identifiant du service", example = "1")
    private Long serviceId;

    @Schema(description = "Nom du service", example = "Coupe homme")
    private String serviceName;

    @Schema(description = "Prix du service en euros", example = "25.00")
    private BigDecimal price;

    @Schema(description = "Durée du service en minutes", example = "30")
    private Integer durationMinutes;

    @Schema(description = "Identifiant du coiffeur", example = "1")
    private Long coiffeurId;

    @Schema(description = "Nom du coiffeur", example = "Marie Martin")
    private String coiffeurName;

    @Schema(description = "Date/heure du rendez-vous", example = "2025-10-20T14:00:00")
    private LocalDateTime startTime;

    @Schema(description = "Statut du rendez-vous", example = "CONFIRMED")
    private String status;

    @Schema(description = "Notes spéciales", example = "Préférence pour une coupe courte")
    private String notes;

    @Schema(description = "Date de création", example = "2025-10-17T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "2025-10-17T12:05:00")
    private LocalDateTime updatedAt;
}
