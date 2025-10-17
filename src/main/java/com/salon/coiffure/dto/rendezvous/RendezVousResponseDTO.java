package com.salon.coiffure.dto.rendezvous;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salon.coiffure.entity.RendezVousStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse complète d'un rendez-vous.
 */
@Schema(description = "Données de réponse d'un rendez-vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVousResponseDTO {

    @Schema(description = "Identifiant unique du rendez-vous", example = "1")
    private Long id;

    @Schema(description = "Date et heure du rendez-vous", example = "2025-10-20T14:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "Statut du rendez-vous", example = "CONFIRMED")
    private RendezVousStatus status;

    @Schema(description = "Notes du rendez-vous", example = "Préférence pour une coupe courte")
    private String notes;

    @Schema(description = "ID de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Nom de l'utilisateur", example = "Jean Dupont")
    private String userName;

    @Schema(description = "ID du service", example = "1")
    private Long serviceId;

    @Schema(description = "Nom du service", example = "Coupe homme")
    private String serviceName;

    @Schema(description = "Prix du service en euros", example = "25.00")
    private BigDecimal servicePrice;

    @Schema(description = "Durée du service en minutes", example = "30")
    private Integer serviceDuration;

    @Schema(description = "ID du coiffeur", example = "1")
    private Long coiffeurId;

    @Schema(description = "Nom du coiffeur", example = "Marie Martin")
    private String coiffeurName;

    @Schema(description = "Date de création", example = "2025-10-17T12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "2025-10-17T12:05:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
