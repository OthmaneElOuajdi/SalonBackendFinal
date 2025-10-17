package com.salon.coiffure.dto.blockedslot;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour un créneau bloqué.
 */
@Schema(description = "Créneau horaire bloqué")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockedSlotDTO {

    @Schema(description = "Identifiant", example = "1")
    private Long id;

    @Schema(description = "Date/heure de début", example = "2025-12-25T00:00:00")
    private LocalDateTime startAt;

    @Schema(description = "Date/heure de fin", example = "2025-12-25T23:59:59")
    private LocalDateTime endAt;

    @Schema(description = "Raison du blocage", example = "Jour férié - Noël")
    private String reason;

    @Schema(description = "ID de l'admin créateur", example = "1")
    private Long createdById;

    @Schema(description = "Nom de l'admin créateur", example = "Admin Principal")
    private String createdByName;

    @Schema(description = "Date de création", example = "2025-10-17T12:00:00")
    private LocalDateTime createdAt;
}
