package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Énumération des rôles disponibles dans l'application salon de coiffure.
 */
@Schema(description = "Rôles disponibles dans le système")
public enum ERole {
    
    @Schema(description = "Client du salon de coiffure")
    ROLE_CLIENT,
    
    @Schema(description = "Coiffeur/employé du salon")
    ROLE_COIFFEUR,
    
    @Schema(description = "Administrateur du système")
    ROLE_ADMIN
}
