package com.salon.coiffure.dto.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse complète d'un service de coiffure.
 */
@Schema(description = "Données de réponse d'un service de coiffure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponseDTO {

    @Schema(description = "Identifiant unique du service", example = "1")
    private Long id;

    @Schema(description = "Nom du service", example = "Coupe homme")
    private String name;

    @Schema(description = "Description détaillée du service", example = "Coupe classique avec shampoing")
    private String description;

    @Schema(description = "Prix du service en euros", example = "25.00")
    private BigDecimal price;

    @Schema(description = "Durée du service en minutes", example = "30")
    private Integer duration;

    @Schema(description = "Points de fidélité accordés", example = "10")
    private Integer loyaltyPointsReward;

    @Schema(description = "Service actuellement disponible", example = "true")
    private Boolean active;

    @Schema(description = "ID de la catégorie", example = "1")
    private Long categoryId;

    @Schema(description = "Nom de la catégorie", example = "Coupe")
    private String categoryName;

    @Schema(description = "Date de création", example = "17/10/2025 12:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "17/10/2025 12:05:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
