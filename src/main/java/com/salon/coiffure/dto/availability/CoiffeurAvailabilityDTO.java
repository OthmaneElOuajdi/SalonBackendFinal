package com.salon.coiffure.dto.availability;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour une disponibilité de coiffeur.
 */
@Schema(description = "Disponibilité d'un coiffeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoiffeurAvailabilityDTO {

    @Schema(description = "Identifiant", example = "1")
    private Long id;

    @Schema(description = "ID du coiffeur", example = "1")
    private Long coiffeurId;

    @Schema(description = "Nom du coiffeur", example = "Marie Martin")
    private String coiffeurName;

    @Schema(description = "Date/heure de début", example = "2025-10-20T09:00:00")
    private LocalDateTime startAt;

    @Schema(description = "Date/heure de fin", example = "2025-10-20T18:00:00")
    private LocalDateTime endAt;

    @Schema(description = "Type (UNAVAILABLE, AVAILABLE, BREAK)", example = "UNAVAILABLE")
    private String type;

    @Schema(description = "Raison", example = "Congés annuels")
    private String reason;
}
