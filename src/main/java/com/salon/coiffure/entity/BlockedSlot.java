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
 * Entité représentant un créneau horaire bloqué.
 * Utilisé pour les congés, maintenance, événements internes, etc.
 */
@Entity
@Table(name = "blocked_slots", indexes = {
        @Index(name = "idx_blockedslot_range", columnList = "start_at,end_at"),
        @Index(name = "idx_blockedslot_admin", columnList = "admin_id")
})
@Schema(description = "Créneaux bloqués (maintenance, congés, événement interne, etc.)")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockedSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du créneau bloqué")
    private Long id;

    @Schema(description = "Date/heure de début du créneau bloqué")
    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Schema(description = "Date/heure de fin du créneau bloqué")
    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Schema(description = "Raison du blocage (ex: Congés annuels, Maintenance)")
    @Column(length = 255)
    private String reason;

    @Schema(description = "Administrateur ayant créé le blocage")
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User createdBy;

    @Schema(description = "Date/heure de création du blocage")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
