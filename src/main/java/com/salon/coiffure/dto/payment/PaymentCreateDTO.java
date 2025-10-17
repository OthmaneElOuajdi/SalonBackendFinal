package com.salon.coiffure.dto.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour créer un nouveau paiement.
 */
@Schema(description = "Données pour créer un nouveau paiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCreateDTO {

    @Schema(description = "Montant du paiement en euros", example = "45.50")
    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double amount;

    @Schema(description = "Méthode de paiement", example = "Carte bancaire")
    @NotNull(message = "La méthode de paiement est obligatoire")
    private String paymentMethod;

    @Schema(description = "ID du rendez-vous", example = "1")
    @NotNull(message = "L'ID du rendez-vous est obligatoire")
    private Long rendezVousId;
}
