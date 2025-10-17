package com.salon.coiffure.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer une catégorie de services.
 */
@Schema(description = "Données pour créer une catégorie de services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategoryCreateDTO {

    @Schema(description = "Nom de la catégorie", example = "Coupe")
    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String name;

    @Schema(description = "Description", example = "Tous les types de coupes")
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    @Schema(description = "Ordre d'affichage", example = "1")
    @Min(value = 0, message = "L'ordre d'affichage ne peut pas être négatif")
    @Builder.Default
    private int displayOrder = 0;
}
