package com.salon.coiffure.dto.availability;

import java.time.LocalDateTime;

import com.salon.coiffure.entity.AvailabilityType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer une disponibilité de coiffeur.
 */
@Schema(description = "Données pour créer une disponibilité")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoiffeurAvailabilityCreateDTO {

    @Schema(description = "ID du coiffeur", example = "1")
    @NotNull(message = "L'ID du coiffeur est obligatoire")
    @Positive(message = "L'ID du coiffeur doit être positif")
    private Long coiffeurId;

    @Schema(description = "Date/heure de début", example = "2025-10-20T09:00:00")
    @NotNull(message = "La date de début est obligatoire")
    @Future(message = "La date de début doit être dans le futur")
    private LocalDateTime startAt;

    @Schema(description = "Date/heure de fin", example = "2025-10-20T18:00:00")
    @NotNull(message = "La date de fin est obligatoire")
    private LocalDateTime endAt;

    @Schema(description = "Type de disponibilité", example = "UNAVAILABLE")
    @NotNull(message = "Le type est obligatoire")
    private AvailabilityType type;

    @Schema(description = "Raison", example = "Congés annuels")
    @Size(max = 500, message = "La raison ne peut pas dépasser 500 caractères")
    private String reason;
}
