package com.salon.coiffure.dto.coiffeur;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour mettre à jour un coiffeur.
 */
@Schema(description = "Données pour mettre à jour un coiffeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoiffeurUpdateDTO {

    @Schema(description = "Nom professionnel", example = "Marie Martin - Expert")
    @Size(min = 2, max = 100, message = "Le nom professionnel doit contenir entre 2 et 100 caractères")
    private String professionalName;

    @Schema(description = "Spécialités", example = "Coloriste, Visagiste, Coupe tendance")
    @Size(max = 500, message = "Les spécialités ne peuvent pas dépasser 500 caractères")
    private String specialties;

    @Schema(description = "Biographie", example = "15 ans d'expérience en coiffure")
    @Size(max = 1000, message = "La biographie ne peut pas dépasser 1000 caractères")
    private String bio;

    @Schema(description = "URL de la photo", example = "https://example.com/photos/marie-new.jpg")
    @Size(max = 500, message = "L'URL de la photo ne peut pas dépasser 500 caractères")
    private String photoUrl;

    @Schema(description = "Coiffeur actif", example = "true")
    private Boolean active;
}
