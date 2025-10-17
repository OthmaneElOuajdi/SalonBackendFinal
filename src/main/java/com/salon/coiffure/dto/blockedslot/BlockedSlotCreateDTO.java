package com.salon.coiffure.dto.blockedslot;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer un créneau bloqué.
 */
@Schema(description = "Données pour créer un créneau bloqué")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockedSlotCreateDTO {

    @Schema(description = "Date/heure de début", example = "2025-12-25T00:00:00")
    @NotNull(message = "La date de début est obligatoire")
    @Future(message = "La date de début doit être dans le futur")
    private LocalDateTime startAt;

    @Schema(description = "Date/heure de fin", example = "2025-12-25T23:59:59")
    @NotNull(message = "La date de fin est obligatoire")
    private LocalDateTime endAt;

    @Schema(description = "Raison du blocage", example = "Jour férié - Noël")
    @Size(max = 255, message = "La raison ne peut pas dépasser 255 caractères")
    private String reason;
}
