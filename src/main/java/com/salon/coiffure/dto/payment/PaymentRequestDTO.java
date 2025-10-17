package com.salon.coiffure.dto.payment;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour une demande de paiement (Stripe ou autre prestataire).
 */
@Schema(description = "Demande de paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    @Schema(description = "Identifiant du rendez-vous à régler", example = "1")
    @NotNull(message = "L'identifiant du rendez-vous est obligatoire")
    private Long rendezVousId;

    @Schema(description = "Montant à payer", example = "45.50")
    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal amount;

    @Schema(description = "Devise ISO", example = "EUR")
    @NotNull(message = "La devise est obligatoire")
    @NotBlank(message = "La devise ne peut pas être vide")
    private String currency;

    @Schema(description = "Identifiant de méthode de paiement", example = "pm_1234567890")
    private String paymentMethodId;
}
