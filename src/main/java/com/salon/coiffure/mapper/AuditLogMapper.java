package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.audit.AuditLogDTO;
import com.salon.coiffure.entity.AuditLog;

/**
 * Mapper responsable de la conversion entre les entités {@link AuditLog}
 * et les DTOs de log d'audit.
 */
@Component
public class AuditLogMapper {

    /**
     * Convertit une entité {@link AuditLog} en {@link AuditLogDTO}.
     */
    public AuditLogDTO toDTO(AuditLog auditLog) {
        if (auditLog == null) {
            return null;
        }

        return AuditLogDTO.builder()
                .id(auditLog.getId())
                .userId(auditLog.getUser() != null ? auditLog.getUser().getId() : null)
                .userName(auditLog.getUser() != null ? auditLog.getUser().getFullName() : null)
                .entityName(auditLog.getEntityName())
                .entityId(auditLog.getEntityId())
                .action(auditLog.getAction())
                .details(auditLog.getDetails())
                .ipAddress(auditLog.getIpAddress())
                .createdAt(auditLog.getCreatedAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link AuditLog} en liste de {@link AuditLogDTO}.
     */
    public List<AuditLogDTO> toDTOList(List<AuditLog> auditLogs) {
        if (auditLogs == null) {
            return null;
        }

        return auditLogs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
