package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Énumération des statuts possibles d'une notification.
 */
@Schema(description = "Statuts d'une notification")
public enum NotificationStatus {
    
    @Schema(description = "Notification non lue")
    UNREAD,
    
    @Schema(description = "Notification lue")
    READ
}
