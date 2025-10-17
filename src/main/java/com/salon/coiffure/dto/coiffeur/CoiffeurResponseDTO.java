package com.salon.coiffure.dto.coiffeur;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse complète d'un coiffeur.
 */
@Schema(description = "Réponse complète d'un coiffeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoiffeurResponseDTO {

    @Schema(description = "Identifiant du coiffeur", example = "1")
    private Long id;

    @Schema(description = "ID de l'utilisateur associé", example = "5")
    private Long userId;

    @Schema(description = "Email de l'utilisateur", example = "marie.martin@example.com")
    private String userEmail;

    @Schema(description = "Nom professionnel", example = "Marie Martin")
    private String professionalName;

    @Schema(description = "Spécialités", example = "Coloriste, Visagiste")
    private String specialties;

    @Schema(description = "Biographie", example = "10 ans d'expérience en coiffure")
    private String bio;

    @Schema(description = "URL de la photo", example = "https://example.com/photos/marie.jpg")
    private String photoUrl;

    @Schema(description = "Coiffeur actif", example = "true")
    private boolean active;

    @Schema(description = "Note moyenne", example = "4.5")
    private Double averageRating;

    @Schema(description = "Nombre d'avis", example = "25")
    private Long reviewCount;

    @Schema(description = "Date de création", example = "15/01/2025 10:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "15/01/2025 10:05:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
