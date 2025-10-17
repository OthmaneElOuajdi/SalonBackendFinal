package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Énumération des statuts possibles d'un rendez-vous.
 */
@Schema(description = "Statuts d'un rendez-vous")
public enum RendezVousStatus {
    
    @Schema(description = "Rendez-vous en attente de confirmation")
    PENDING,
    
    @Schema(description = "Rendez-vous confirmé")
    CONFIRMED,
    
    @Schema(description = "Rendez-vous annulé")
    CANCELED,
    
    @Schema(description = "Rendez-vous complété")
    COMPLETED,
    
    @Schema(description = "Client absent (no-show)")
    NO_SHOW
}
