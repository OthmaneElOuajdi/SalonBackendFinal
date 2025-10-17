package com.salon.coiffure.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un utilisateur du système de salon de coiffure.
 * Peut avoir plusieurs rôles (client, coiffeur, administrateur).
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email", columnList = "email", unique = true)
})
@Schema(description = "Représente un utilisateur de l'application salon de coiffure")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de l'utilisateur")
    private Long id;

    @Schema(description = "Nom complet de l'utilisateur")
    @Column(name = "full_name", nullable = false, length = 100)
    @NotBlank(message = "Le nom complet est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String fullName;

    @Schema(description = "Adresse e-mail unique utilisée pour la connexion")
    @Column(nullable = false, unique = true, length = 150)
    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @Schema(description = "Mot de passe haché")
    @Column(nullable = false)
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    @Schema(description = "Numéro de téléphone")
    @Column(length = 20)
    private String phone;

    @Schema(description = "Date d'inscription")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "Date de dernière connexion")
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Schema(description = "Points de fidélité accumulés")
    @Column(name = "loyalty_points", nullable = false)
    @Builder.Default
    private int loyaltyPoints = 0;

    @Schema(description = "Indique si le compte est actif")
    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Schema(description = "Rôles attribués à l'utilisateur (ex: ROLE_CLIENT, ROLE_COIFFEUR, ROLE_ADMIN)")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    @Schema(description = "Rendez-vous associés à l'utilisateur")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RendezVous> rendezVous = new HashSet<>();

    @Schema(description = "Logs d'audit associés à cet utilisateur")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<AuditLog> auditLogs = new HashSet<>();

    // Hooks JPA
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
