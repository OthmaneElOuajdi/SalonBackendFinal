package com.salon.coiffure.entity;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant l'historique des actions effectuées dans le système.
 * Utilisée pour la traçabilité, la sécurité et l'administration.
 */
@Entity
@Table(name = "audit_logs", indexes = {
        @Index(name = "idx_audit_entity", columnList = "entity_name,entity_id"),
        @Index(name = "idx_audit_created_at", columnList = "created_at"),
        @Index(name = "idx_audit_action", columnList = "action")
})
@Schema(description = "Historique des actions (sécurité, administration, système)")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du log d'audit")
    private Long id;

    @Schema(description = "Utilisateur à l'origine de l'action (null si action système)")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Schema(description = "Nom de l'entité affectée (ex: 'RendezVous', 'User', 'Service')")
    @Column(name = "entity_name", nullable = false, length = 100)
    private String entityName;

    @Schema(description = "Identifiant de l'entité affectée")
    @Column(name = "entity_id")
    private Long entityId;

    @Schema(description = "Action réalisée (CREATE, UPDATE, DELETE, LOGIN, LOGOUT, etc.)")
    @Column(nullable = false, length = 50)
    private String action;

    @Schema(description = "Détails supplémentaires de l'action (JSON ou texte libre)")
    @Column(columnDefinition = "TEXT")
    private String details;

    @Schema(description = "Adresse IP de l'utilisateur ayant effectué l'action")
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Schema(description = "Date et heure de création du log")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     * Initialise automatiquement la date de création.
     */
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
