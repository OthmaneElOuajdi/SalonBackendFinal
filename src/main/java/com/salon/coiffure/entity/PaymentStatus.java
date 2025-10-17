package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Énumération des statuts possibles d'un paiement.
 */
@Schema(description = "Statuts d'un paiement")
public enum PaymentStatus {
    
    @Schema(description = "Paiement en attente")
    PENDING,
    
    @Schema(description = "Paiement complété avec succès")
    COMPLETED,
    
    @Schema(description = "Paiement échoué")
    FAILED,
    
    @Schema(description = "Paiement remboursé")
    REFUNDED,
    
    @Schema(description = "Paiement annulé")
    CANCELLED
}
