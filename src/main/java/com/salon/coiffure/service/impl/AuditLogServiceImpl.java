package com.salon.coiffure.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.AuditLog;
import com.salon.coiffure.entity.User;
import com.salon.coiffure.repository.AuditLogRepository;
import com.salon.coiffure.repository.UserRepository;
import com.salon.coiffure.service.AuditLogService;

import lombok.RequiredArgsConstructor;

/**
 * Implémentation du service d'audit.
 * Gère la logique de création des logs.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    @Override
    public AuditLog logUserAction(Long userId, String entityName, Long entityId, String action, String details) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        AuditLog log = AuditLog.builder()
                .user(user)
                .entityName(entityName)
                .entityId(entityId)
                .action(action)
                .details(details)
                .build();

        return auditLogRepository.save(log);
    }

    @Override
    public AuditLog logSystemAction(String entityName, Long entityId, String action, String details) {
        AuditLog log = AuditLog.builder()
                .entityName(entityName)
                .entityId(entityId)
                .action(action)
                .details(details)
                .build();

        return auditLogRepository.save(log);
    }

    @Override
    public List<AuditLog> last(int limit) {
        return auditLogRepository.findAll(PageRequest.of(0, Math.max(1, limit))).getContent();
    }
}
