package com.salon.coiffure.dto.rendezvous;

import java.time.LocalDateTime;

import com.salon.coiffure.entity.RendezVousStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour mettre à jour un rendez-vous existant.
 */
@Schema(description = "Données pour mettre à jour un rendez-vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVousUpdateDTO {

    @Schema(description = "Nouvelle date et heure du rendez-vous", example = "2025-10-20T15:00:00")
    @Future(message = "La date du rendez-vous doit être dans le futur")
    private LocalDateTime startTime;

    @Schema(description = "Nouveau statut du rendez-vous", example = "CONFIRMED")
    private RendezVousStatus status;

    @Schema(description = "Nouvelles notes", example = "Changement d'horaire demandé")
    @Size(max = 1000, message = "Les notes ne peuvent pas dépasser 1000 caractères")
    private String notes;

    @Schema(description = "Nouvel ID du coiffeur", example = "2")
    private Long coiffeurId;
}
