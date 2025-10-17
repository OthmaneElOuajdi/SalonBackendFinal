package com.salon.coiffure.dto.service;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer un nouveau service de coiffure.
 */
@Schema(description = "Données pour créer un nouveau service de coiffure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCreateDTO {

    @Schema(description = "Nom du service", example = "Coupe homme")
    @NotBlank(message = "Le nom du service est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String name;

    @Schema(description = "Description détaillée du service", example = "Coupe classique avec shampoing")
    @Size(max = 1000, message = "La description ne peut pas dépasser 1000 caractères")
    private String description;

    @Schema(description = "Prix du service en euros", example = "25.00")
    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    @Digits(integer = 6, fraction = 2, message = "Le prix doit avoir au maximum 6 chiffres avant la virgule et 2 après")
    private BigDecimal price;

    @Schema(description = "Durée du service en minutes", example = "30")
    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être d'au moins 1 minute")
    @Max(value = 480, message = "La durée ne peut pas dépasser 8 heures (480 minutes)")
    private Integer duration;

    @Schema(description = "Points de fidélité accordés", example = "10")
    @Min(value = 0, message = "Les points de récompense ne peuvent pas être négatifs")
    @Builder.Default
    private Integer loyaltyPointsReward = 0;

    @Schema(description = "Service disponible à la réservation", example = "true")
    @Builder.Default
    private Boolean active = true;

    @Schema(description = "ID de la catégorie du service", example = "1")
    @Positive(message = "L'ID de la catégorie doit être positif")
    private Long categoryId;
}
