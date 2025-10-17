package com.salon.coiffure.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer un avis client.
 */
@Schema(description = "Données pour créer un avis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCreateDTO {

    @Schema(description = "ID du coiffeur", example = "1")
    @NotNull(message = "L'ID du coiffeur est obligatoire")
    @Positive(message = "L'ID du coiffeur doit être positif")
    private Long coiffeurId;

    @Schema(description = "ID du rendez-vous (optionnel)", example = "5")
    @Positive(message = "L'ID du rendez-vous doit être positif")
    private Long rendezVousId;

    @Schema(description = "Note de 1 à 5", example = "5")
    @NotNull(message = "La note est obligatoire")
    @Min(value = 1, message = "La note minimale est 1")
    @Max(value = 5, message = "La note maximale est 5")
    private Integer rating;

    @Schema(description = "Commentaire", example = "Excellent service, très professionnel!")
    @Size(max = 1000, message = "Le commentaire ne peut pas dépasser 1000 caractères")
    private String comment;
}
