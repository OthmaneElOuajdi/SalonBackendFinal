package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Énumération des types de notifications disponibles.
 */
@Schema(description = "Types de notifications dans le système")
public enum NotificationType {
    
    @Schema(description = "Notification système générale")
    SYSTEM,
    
    @Schema(description = "Notification liée à un rendez-vous")
    BOOKING,
    
    @Schema(description = "Notification liée à un paiement")
    PAYMENT,
    
    @Schema(description = "Notification de rappel")
    REMINDER,
    
    @Schema(description = "Notification promotionnelle")
    PROMOTION,
    
    @Schema(description = "Notification de confirmation")
    CONFIRMATION,
    
    @Schema(description = "Notification d'annulation")
    CANCELLATION
}
