package com.salon.coiffure.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un coiffeur/employé du salon.
 */
@Entity
@Table(name = "coiffeurs", indexes = {
        @Index(name = "idx_coiffeur_user", columnList = "user_id"),
        @Index(name = "idx_coiffeur_active", columnList = "active")
})
@Schema(description = "Coiffeur/employé du salon de coiffure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coiffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du coiffeur")
    private Long id;

    @Schema(description = "Utilisateur associé au coiffeur")
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Schema(description = "Nom professionnel du coiffeur")
    @Column(name = "professional_name", nullable = false, length = 100)
    @NotBlank(message = "Le nom professionnel est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom professionnel doit contenir entre 2 et 100 caractères")
    private String professionalName;

    @Schema(description = "Spécialités du coiffeur (ex: Coloriste, Visagiste)")
    @Column(columnDefinition = "TEXT")
    private String specialties;

    @Schema(description = "Biographie ou description du coiffeur")
    @Column(columnDefinition = "TEXT")
    private String bio;

    @Schema(description = "URL de la photo de profil du coiffeur")
    @Column(name = "photo_url", length = 500)
    private String photoUrl;

    @Schema(description = "Indique si le coiffeur est actif et disponible pour les rendez-vous")
    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Schema(description = "Date de création du profil coiffeur")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "Rendez-vous assignés à ce coiffeur")
    @OneToMany(mappedBy = "coiffeur", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<RendezVous> rendezVous = new HashSet<>();

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
