package com.salon.coiffure.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la mise à jour d'un utilisateur.
 */
@Schema(description = "Données pour mettre à jour un utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {

    @Schema(description = "Nom complet de l'utilisateur", example = "Jean Dupont")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String fullName;

    @Schema(description = "Nouveau mot de passe", example = "nouveaumotdepasse123")
    @Size(min = 6, max = 100, message = "Le mot de passe doit contenir entre 6 et 100 caractères")
    private String password;

    @Schema(description = "Numéro de téléphone", example = "+33612345678")
    private String phone;
}
