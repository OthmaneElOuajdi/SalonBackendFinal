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
 * Entité représentant une notification envoyée à un utilisateur.
 */
@Entity
@Table(name = "notifications", indexes = {
        @Index(name = "idx_notification_user", columnList = "user_id"),
        @Index(name = "idx_notification_status", columnList = "status"),
        @Index(name = "idx_notification_sent_at", columnList = "sent_at")
})
@Schema(description = "Notification envoyée à un utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la notification")
    private Long id;

    @Schema(description = "Utilisateur qui reçoit la notification")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Schema(description = "Type de notification (ex: SYSTEM, BOOKING, PAYMENT)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private NotificationType type;

    @Schema(description = "Message de la notification")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Schema(description = "Statut actuel de la notification (UNREAD, READ)")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private NotificationStatus status = NotificationStatus.UNREAD;

    @Schema(description = "Date et heure d'envoi de la notification")
    @Column(name = "sent_at", nullable = false, updatable = false)
    private LocalDateTime sentAt;

    @Schema(description = "Date et heure à laquelle la notification a été lue")
    @Column(name = "read_at")
    private LocalDateTime readAt;

    /**
     * Hook JPA exécuté avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        this.sentAt = LocalDateTime.now();
    }
}
