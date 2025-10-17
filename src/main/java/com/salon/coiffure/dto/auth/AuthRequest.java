package com.salon.coiffure.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la requête d'authentification (login).
 */
@Schema(description = "Requête d'authentification (login)")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {

    @Schema(description = "Adresse e-mail de l'utilisateur", example = "client@example.com")
    @NotBlank(message = "L'adresse e-mail est obligatoire")
    @Email(message = "Format d'adresse e-mail invalide")
    private String email;

    @Schema(description = "Mot de passe en texte clair (transmis via HTTPS)", example = "motdepasse123")
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, max = 100, message = "Le mot de passe doit contenir entre 6 et 100 caractères")
    private String password;
}
