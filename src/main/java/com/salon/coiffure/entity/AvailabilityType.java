package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Énumération des types de disponibilité d'un coiffeur.
 */
@Schema(description = "Types de disponibilité d'un coiffeur")
public enum AvailabilityType {
    
    @Schema(description = "Coiffeur indisponible (congés, absence)")
    UNAVAILABLE,
    
    @Schema(description = "Coiffeur disponible (disponibilité exceptionnelle)")
    AVAILABLE,
    
    @Schema(description = "Pause/break du coiffeur")
    BREAK
}
