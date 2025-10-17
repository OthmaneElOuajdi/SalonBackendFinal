package com.salon.coiffure.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse après une tentative de paiement.
 */
@Schema(description = "Réponse après tentative de paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    @Schema(description = "Identifiant du paiement", example = "1")
    private Long id;

    @Schema(description = "Montant du paiement", example = "45.50")
    private BigDecimal amount;

    @Schema(description = "Devise du paiement", example = "EUR")
    private String currency;

    @Schema(description = "Statut du paiement", example = "COMPLETED")
    private String status;

    @Schema(description = "ID de transaction externe (PaymentIntent ID)", example = "pi_1234567890")
    private String transactionId;

    @Schema(description = "ID du PaymentIntent Stripe", example = "pi_1234567890")
    private String paymentIntentId;

    @Schema(description = "Client Secret pour Stripe Elements", example = "pi_1234567890_secret_abcdef")
    private String clientSecret;

    @Schema(description = "Message ou erreur éventuelle", example = "Paiement effectué avec succès")
    private String message;

    @Schema(description = "ID de l'utilisateur", example = "1")
    private Long userId;

    @Schema(description = "Nom de l'utilisateur", example = "Jean Dupont")
    private String userName;

    @Schema(description = "ID du rendez-vous", example = "1")
    private Long rendezVousId;

    @Schema(description = "Date/heure du rendez-vous", example = "2025-10-17T14:00:00")
    private LocalDateTime rendezVousDateTime;

    @Schema(description = "Méthode de paiement utilisée", example = "Carte bancaire")
    private String paymentMethod;

    @Schema(description = "Date de création du paiement", example = "2025-10-17T12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Date de dernière modification", example = "2025-10-17T12:05:00")
    private LocalDateTime updatedAt;
}
