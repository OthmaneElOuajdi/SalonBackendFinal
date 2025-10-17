package com.salon.coiffure.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour un rôle utilisateur.
 */
@Schema(description = "Rôle utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {

    @Schema(description = "Identifiant du rôle", example = "1")
    private Long id;

    @Schema(description = "Nom du rôle", example = "ROLE_CLIENT")
    private String name;

    @Schema(description = "Description du rôle", example = "Client du salon")
    private String description;
}
