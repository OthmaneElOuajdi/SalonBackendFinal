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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un paiement effectué par un utilisateur.
 */
@Entity
@Table(name = "payments", indexes = {
        @Index(name = "idx_payment_user", columnList = "user_id"),
        @Index(name = "idx_payment_rendez_vous", columnList = "rendez_vous_id"),
        @Index(name = "idx_payment_status", columnList = "status"),
        @Index(name = "idx_payment_date", columnList = "payment_date")
})
@Schema(description = "Représente un paiement effectué par un utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du paiement")
    private Long id;

    @Schema(description = "Utilisateur ayant effectué le paiement")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Rendez-vous associé au paiement")
    @OneToOne(optional = false)
    @JoinColumn(name = "rendez_vous_id", nullable = false)
    private RendezVous rendezVous;

    @Schema(description = "Montant du paiement en euros")
    @Column(nullable = false)
    private double amount;

    @Schema(description = "Statut du paiement (PENDING, COMPLETED, FAILED)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Schema(description = "Message lié au paiement (erreur, confirmation, etc.)")
    @Column(length = 500)
    private String message;

    @Schema(description = "Date et heure du paiement")
    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @Schema(description = "Méthode de paiement utilisée (ex: Carte, PayPal, Virement)")
    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Schema(description = "Identifiant de transaction externe (fourni par le prestataire de paiement)")
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Schema(description = "Date de création de l'enregistrement")
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification de l'enregistrement")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();
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
