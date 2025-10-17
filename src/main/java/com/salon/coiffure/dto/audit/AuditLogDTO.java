package com.salon.coiffure.dto.audit;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour un log d'audit (lecture seule).
 */
@Schema(description = "Log d'audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogDTO {

    @Schema(description = "Identifiant du log", example = "1")
    private Long id;

    @Schema(description = "ID de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Nom de l'utilisateur", example = "Jean Dupont")
    private String userName;

    @Schema(description = "Nom de l'entité affectée", example = "RendezVous")
    private String entityName;

    @Schema(description = "ID de l'entité affectée", example = "5")
    private Long entityId;

    @Schema(description = "Action réalisée", example = "CREATE")
    private String action;

    @Schema(description = "Détails de l'action", example = "{\"status\":\"CONFIRMED\"}")
    private String details;

    @Schema(description = "Adresse IP", example = "192.168.1.1")
    private String ipAddress;

    @Schema(description = "Date de création", example = "17/10/2025 12:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
