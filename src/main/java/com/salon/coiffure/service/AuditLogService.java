package com.salon.coiffure.service;

import java.util.List;

import com.salon.coiffure.entity.AuditLog;

/**
 * Interface du service d'audit.
 * Gère la création et la récupération des logs d'audit.
 */
public interface AuditLogService {

    /**
     * Enregistre une action utilisateur dans les logs d'audit.
     *
     * @param userId L'ID de l'utilisateur
     * @param entityName Le nom de l'entité affectée
     * @param entityId L'ID de l'entité affectée
     * @param action L'action effectuée
     * @param details Détails supplémentaires
     * @return Le log d'audit créé
     */
    AuditLog logUserAction(Long userId, String entityName, Long entityId, String action, String details);

    /**
     * Enregistre une action système dans les logs d'audit.
     *
     * @param entityName Le nom de l'entité affectée
     * @param entityId L'ID de l'entité affectée
     * @param action L'action effectuée
     * @param details Détails supplémentaires
     * @return Le log d'audit créé
     */
    AuditLog logSystemAction(String entityName, Long entityId, String action, String details);

    /**
     * Récupère les derniers logs d'audit.
     *
     * @param limit Nombre maximum de logs à récupérer
     * @return Liste des derniers logs
     */
    List<AuditLog> last(int limit);
}
