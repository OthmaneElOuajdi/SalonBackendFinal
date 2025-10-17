package com.salon.coiffure.dto.service;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour un service proposé par le salon.
 */
@Schema(description = "Service proposé par le salon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDTO {

    @Schema(description = "Identifiant du service", example = "1")
    private Long id;

    @Schema(description = "Nom du service", example = "Coupe homme")
    @NotBlank(message = "Le nom du service est obligatoire")
    private String name;

    @Schema(description = "Description du service", example = "Coupe classique avec shampoing")
    private String description;

    @Schema(description = "Prix TTC en euros", example = "25.00")
    @Positive(message = "Le prix doit être positif")
    private BigDecimal price;

    @Schema(description = "Durée en minutes", example = "30")
    @Positive(message = "La durée doit être positive")
    private Integer duration;

    @Schema(description = "Points de fidélité gagnés", example = "10")
    private Integer loyaltyPointsReward;

    @Schema(description = "Service actif et disponible", example = "true")
    private Boolean active;

    @Schema(description = "ID de la catégorie", example = "1")
    private Long categoryId;

    @Schema(description = "Nom de la catégorie", example = "Coupe")
    private String categoryName;
}
