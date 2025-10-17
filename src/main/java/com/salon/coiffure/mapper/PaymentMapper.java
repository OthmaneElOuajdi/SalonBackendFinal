package com.salon.coiffure.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.payment.PaymentCreateDTO;
import com.salon.coiffure.dto.payment.PaymentResponseDTO;
import com.salon.coiffure.entity.Payment;

/**
 * Mapper responsable de la conversion entre les entités {@link Payment}
 * et les différents DTOs de paiement.
 */
@Component
public class PaymentMapper {

    /**
     * Convertit une entité {@link Payment} en {@link PaymentResponseDTO}.
     *
     * @param payment l'entité {@link Payment} à convertir
     * @return une instance de {@link PaymentResponseDTO}
     */
    public PaymentResponseDTO toResponseDTO(Payment payment) {
        if (payment == null) {
            return null;
        }

        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(BigDecimal.valueOf(payment.getAmount()))
                .currency("EUR")
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus().toString())
                .transactionId(payment.getTransactionId())
                .paymentIntentId(payment.getTransactionId())
                .message(payment.getMessage())
                .userId(payment.getUser() != null ? payment.getUser().getId() : null)
                .userName(payment.getUser() != null ? payment.getUser().getFullName() : null)
                .rendezVousId(payment.getRendezVous() != null ? payment.getRendezVous().getId() : null)
                .rendezVousDateTime(payment.getRendezVous() != null ? payment.getRendezVous().getStartTime() : null)
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link Payment} en liste de {@link PaymentResponseDTO}.
     *
     * @param payments la liste d'entités {@link Payment} à convertir
     * @return une liste de {@link PaymentResponseDTO}
     */
    public List<PaymentResponseDTO> toResponseDTOList(List<Payment> payments) {
        if (payments == null) {
            return null;
        }

        return payments.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link PaymentCreateDTO} en entité {@link Payment}.
     *
     * @param dto le {@link PaymentCreateDTO} à convertir
     * @return une nouvelle entité {@link Payment}
     */
    public Payment toEntity(PaymentCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Payment.builder()
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                // Le statut, le rendezVous et l'utilisateur seront définis par le service
                .build();
    }
}
