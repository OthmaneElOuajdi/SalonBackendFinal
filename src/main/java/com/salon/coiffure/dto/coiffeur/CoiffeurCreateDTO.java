package com.salon.coiffure.dto.coiffeur;

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
 * DTO pour créer un nouveau coiffeur.
 */
@Schema(description = "Données pour créer un nouveau coiffeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoiffeurCreateDTO {

    @Schema(description = "ID de l'utilisateur à associer", example = "5")
    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    @Positive(message = "L'ID de l'utilisateur doit être positif")
    private Long userId;

    @Schema(description = "Nom professionnel", example = "Marie Martin")
    @NotBlank(message = "Le nom professionnel est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom professionnel doit contenir entre 2 et 100 caractères")
    private String professionalName;

    @Schema(description = "Spécialités", example = "Coloriste, Visagiste")
    @Size(max = 500, message = "Les spécialités ne peuvent pas dépasser 500 caractères")
    private String specialties;

    @Schema(description = "Biographie", example = "10 ans d'expérience en coiffure")
    @Size(max = 1000, message = "La biographie ne peut pas dépasser 1000 caractères")
    private String bio;

    @Schema(description = "URL de la photo", example = "https://example.com/photos/marie.jpg")
    @Size(max = 500, message = "L'URL de la photo ne peut pas dépasser 500 caractères")
    private String photoUrl;
}
