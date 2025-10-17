package com.salon.coiffure.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse d'authentification contenant les jetons JWT.
 */
@Schema(description = "Réponse d'authentification contenant les jetons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    @Schema(description = "Jeton d'accès JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "Jeton de rafraîchissement", example = "550e8400-e29b-41d4-a716-446655440000")
    private String refreshToken;

    @Schema(description = "Type de jeton", example = "Bearer")
    @Builder.Default
    private String tokenType = "Bearer";

    @Schema(description = "Durée de validité du jeton d'accès en secondes", example = "3600")
    private long expiresIn;
}
