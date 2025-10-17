package com.salon.coiffure.dto.review;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse complète d'un avis.
 */
@Schema(description = "Réponse complète d'un avis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDTO {

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

    @Schema(description = "ID du rendez-vous", example = "5")
    private Long rendezVousId;

    @Schema(description = "Note (1-5)", example = "5")
    private int rating;

    @Schema(description = "Commentaire", example = "Excellent service!")
    private String comment;

    @Schema(description = "Avis approuvé", example = "true")
    private boolean approved;

    @Schema(description = "Date de création", example = "17/10/2025 12:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "17/10/2025 12:05:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
