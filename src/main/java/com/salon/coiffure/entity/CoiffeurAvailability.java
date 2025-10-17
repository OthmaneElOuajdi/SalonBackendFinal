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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant les disponibilités/indisponibilités d'un coiffeur spécifique.
 * Permet de gérer les congés, absences, et disponibilités exceptionnelles.
 */
@Entity
@Table(name = "coiffeur_availabilities", indexes = {
        @Index(name = "idx_availability_coiffeur", columnList = "coiffeur_id"),
        @Index(name = "idx_availability_range", columnList = "start_at,end_at"),
        @Index(name = "idx_availability_type", columnList = "type")
})
@Schema(description = "Disponibilités et indisponibilités d'un coiffeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoiffeurAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la disponibilité")
    private Long id;

    @Schema(description = "Coiffeur concerné")
    @ManyToOne(optional = false)
    @JoinColumn(name = "coiffeur_id", nullable = false)
    private Coiffeur coiffeur;

    @Schema(description = "Date/heure de début de la période")
    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Schema(description = "Date/heure de fin de la période")
    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Schema(description = "Type de disponibilité (UNAVAILABLE, AVAILABLE, BREAK)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AvailabilityType type;

    @Schema(description = "Raison de l'indisponibilité (ex: Congés, Formation, Maladie)")
    @Column(length = 500)
    private String reason;

    @Schema(description = "Date de création de l'enregistrement")
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
