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
 * Entité représentant un token d'actualisation pour l'authentification JWT.
 * Permet de renouveler les access tokens sans redemander les identifiants.
 */
@Entity
@Table(name = "refresh_tokens", indexes = {
        @Index(name = "idx_refreshtoken_token", columnList = "token", unique = true),
        @Index(name = "idx_refreshtoken_user", columnList = "user_id"),
        @Index(name = "idx_refreshtoken_expires", columnList = "expires_at")
})
@Schema(description = "Token d'actualisation pour l'authentification JWT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du refresh token")
    private Long id;

    @Schema(description = "Utilisateur propriétaire du refresh token")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Valeur opaque du token (longue et aléatoire)")
    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Schema(description = "Date/heure d'expiration du token")
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Schema(description = "Indique si le token est révoqué")
    @Column(nullable = false)
    @Builder.Default
    private boolean revoked = false;

    @Schema(description = "Date/heure de création du token")
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
