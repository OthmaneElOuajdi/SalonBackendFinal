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
 * Entité représentant une transaction de points de fidélité.
 * Permet de tracer l'historique des gains et dépenses de points.
 */
@Entity
@Table(name = "loyalty_point_transactions", indexes = {
        @Index(name = "idx_loyalty_tx_user", columnList = "user_id"),
        @Index(name = "idx_loyalty_tx_date", columnList = "transaction_date")
})
@Schema(description = "Historique des transactions de points de fidélité")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyPointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la transaction de points")
    private Long id;

    @Schema(description = "Utilisateur concerné par la transaction")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Nombre de points gagnés (positif) ou dépensés (négatif)")
    @Column(nullable = false)
    private int points;

    @Schema(description = "Description libre de la transaction (ex: 'Rendez-vous complété', 'Récompense utilisée')")
    @Column(length = 500)
    private String description;

    @Schema(description = "Date/heure de la transaction")
    @Column(name = "transaction_date", nullable = false, updatable = false)
    private LocalDateTime transactionDate;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        this.transactionDate = LocalDateTime.now();
    }
}
