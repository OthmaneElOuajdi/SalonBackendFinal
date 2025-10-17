package com.salon.coiffure.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour une catégorie de services.
 */
@Schema(description = "Catégorie de services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategoryDTO {

    @Schema(description = "Identifiant de la catégorie", example = "1")
    private Long id;

    @Schema(description = "Nom de la catégorie", example = "Coupe")
    private String name;

    @Schema(description = "Description", example = "Tous les types de coupes")
    private String description;

    @Schema(description = "Ordre d'affichage", example = "1")
    private int displayOrder;

    @Schema(description = "Catégorie active", example = "true")
    private boolean active;

    @Schema(description = "Nombre de services", example = "5")
    private int serviceCount;
}
