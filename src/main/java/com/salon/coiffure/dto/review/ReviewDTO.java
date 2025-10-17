package com.salon.coiffure.dto.review;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour un avis client.
 */
@Schema(description = "Avis client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    @Schema(description = "Identifiant de l'avis", example = "1")
    private Long id;

    @Schema(description = "ID de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Nom de l'utilisateur", example = "Jean Dupont")
    private String userName;

    @Schema(description = "ID du coiffeur", example = "1")
    private Long coiffeurId;

    @Schema(description = "Nom du coiffeur", example = "Marie Martin")
    private String coiffeurName;

    @Schema(description = "Note (1-5)", example = "5")
    private int rating;

    @Schema(description = "Commentaire", example = "Excellent service!")
    private String comment;

    @Schema(description = "Avis approuvé", example = "true")
    private boolean approved;

    @Schema(description = "Date de création", example = "2025-10-17T12:00:00")
    private LocalDateTime createdAt;
}
