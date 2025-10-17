package com.salon.coiffure.dto.workinghours;

import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour les horaires d'ouverture.
 */
@Schema(description = "Horaires d'ouverture du salon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkingHoursDTO {

    @Schema(description = "Identifiant", example = "1")
    private Long id;

    @Schema(description = "Jour de la semaine", example = "MONDAY")
    private String dayOfWeek;

    @Schema(description = "Heure d'ouverture", example = "09:00")
    private LocalTime startTime;

    @Schema(description = "Heure de fermeture", example = "18:00")
    private LocalTime endTime;

    @Schema(description = "Salon fermé ce jour", example = "false")
    private boolean closed;
}
