package com.salon.coiffure.dto.user;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse complète d'un utilisateur.
 */
@Schema(description = "Données de réponse d'un utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    @Schema(description = "Identifiant unique", example = "1")
    private Long id;

    @Schema(description = "Nom complet", example = "Jean Dupont")
    private String fullName;

    @Schema(description = "Adresse email", example = "jean.dupont@example.com")
    private String email;

    @Schema(description = "Numéro de téléphone", example = "+33612345678")
    private String phone;

    @Schema(description = "Points de fidélité", example = "250")
    private int loyaltyPoints;

    @Schema(description = "Compte actif", example = "true")
    private boolean active;

    @Schema(description = "Rôles de l'utilisateur", example = "[\"ROLE_CLIENT\"]")
    private Set<String> roles;

    @Schema(description = "Date de création", example = "17/10/2025 12:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "17/10/2025 12:05:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema(description = "Date de dernière connexion", example = "17/10/2025 12:30:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastLoginAt;
}
