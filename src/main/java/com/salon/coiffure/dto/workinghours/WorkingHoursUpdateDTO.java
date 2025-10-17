package com.salon.coiffure.dto.workinghours;

import java.time.DayOfWeek;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour mettre à jour les horaires d'ouverture.
 */
@Schema(description = "Données pour mettre à jour les horaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkingHoursUpdateDTO {

    @Schema(description = "Jour de la semaine", example = "MONDAY")
    private DayOfWeek dayOfWeek;

    @Schema(description = "Heure d'ouverture", example = "09:00")
    private LocalTime startTime;

    @Schema(description = "Heure de fermeture", example = "18:00")
    private LocalTime endTime;

    @Schema(description = "Salon fermé ce jour", example = "false")
    private Boolean closed;
}
