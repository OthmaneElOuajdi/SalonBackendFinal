package com.salon.coiffure.entity;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un rendez-vous au salon de coiffure.
 */
@Entity
@Table(name = "rendez_vous", indexes = {
        @Index(name = "idx_rdv_user", columnList = "user_id"),
        @Index(name = "idx_rdv_service", columnList = "service_id"),
        @Index(name = "idx_rdv_coiffeur", columnList = "coiffeur_id"),
        @Index(name = "idx_rdv_start_time", columnList = "start_time"),
        @Index(name = "idx_rdv_status", columnList = "status")
})
@Schema(description = "Rendez-vous d'un client au salon de coiffure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du rendez-vous", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Client qui effectue le rendez-vous")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Service réservé par le client")
    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Schema(description = "Coiffeur assigné au rendez-vous")
    @ManyToOne
    @JoinColumn(name = "coiffeur_id")
    private Coiffeur coiffeur;

    @Schema(description = "Date/heure de début du rendez-vous")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Schema(description = "Statut actuel du rendez-vous (PENDING, CONFIRMED, CANCELED, COMPLETED)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private RendezVousStatus status = RendezVousStatus.PENDING;

    @Schema(description = "Notes du client ou du coiffeur")
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Schema(description = "Date/heure de création du rendez-vous")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Date/heure de dernière modification")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = RendezVousStatus.PENDING;
        }
    }

    /**
     * Hook JPA exécuté avant la mise à jour de l'entité.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
