package com.salon.coiffure.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la requête de renouvellement d'un JWT à partir d'un refresh token.
 */
@Schema(description = "Requête pour renouveler un JWT à partir d'un refresh token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenRequest {

    @Schema(description = "Refresh token valide", example = "550e8400-e29b-41d4-a716-446655440000")
    @NotBlank(message = "Le refresh token est obligatoire")
    private String refreshToken;
}
