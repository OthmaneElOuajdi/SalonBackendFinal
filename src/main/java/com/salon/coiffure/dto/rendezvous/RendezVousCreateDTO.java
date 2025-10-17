package com.salon.coiffure.dto.rendezvous;

import java.time.LocalDateTime;

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
 * DTO pour créer un nouveau rendez-vous.
 */
@Schema(description = "Données pour créer un nouveau rendez-vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVousCreateDTO {

    @Schema(description = "ID du service à réserver", example = "1")
    @NotNull(message = "L'ID du service est obligatoire")
    @Positive(message = "L'ID du service doit être positif")
    private Long serviceId;

    @Schema(description = "ID du coiffeur souhaité (optionnel)", example = "1")
    @Positive(message = "L'ID du coiffeur doit être positif")
    private Long coiffeurId;

    @Schema(description = "Date et heure du rendez-vous", example = "2025-10-20T14:00:00")
    @NotNull(message = "La date du rendez-vous est obligatoire")
    @Future(message = "Le rendez-vous doit être dans le futur")
    private LocalDateTime startTime;

    @Schema(description = "Notes spéciales pour le rendez-vous", example = "Préférence pour une coupe courte")
    @Size(max = 1000, message = "Les notes ne peuvent pas dépasser 1000 caractères")
    private String notes;
}
