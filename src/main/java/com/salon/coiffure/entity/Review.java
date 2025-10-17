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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un avis client sur un service ou un coiffeur.
 */
@Entity
@Table(name = "reviews", indexes = {
        @Index(name = "idx_review_user", columnList = "user_id"),
        @Index(name = "idx_review_coiffeur", columnList = "coiffeur_id"),
        @Index(name = "idx_review_rendez_vous", columnList = "rendez_vous_id"),
        @Index(name = "idx_review_rating", columnList = "rating")
})
@Schema(description = "Avis client sur un service ou un coiffeur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'avis")
    private Long id;

    @Schema(description = "Client ayant laissé l'avis")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Coiffeur évalué")
    @ManyToOne
    @JoinColumn(name = "coiffeur_id")
    private Coiffeur coiffeur;

    @Schema(description = "Rendez-vous associé à l'avis")
    @ManyToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;

    @Schema(description = "Note de 1 à 5 étoiles")
    @Column(nullable = false)
    @Min(value = 1, message = "La note minimale est 1")
    @Max(value = 5, message = "La note maximale est 5")
    private int rating;

    @Schema(description = "Commentaire du client")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Schema(description = "Indique si l'avis est approuvé et visible publiquement")
    @Column(nullable = false)
    @Builder.Default
    private boolean approved = false;

    @Schema(description = "Date de création de l'avis")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Hook JPA exécuté avant la mise à jour de l'entité.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
