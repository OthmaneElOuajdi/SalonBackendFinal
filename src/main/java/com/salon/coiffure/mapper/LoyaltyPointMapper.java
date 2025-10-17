package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.loyalty.LoyaltyPointDTO;
import com.salon.coiffure.entity.LoyaltyPointTransaction;

/**
 * Mapper responsable de la conversion entre les entités {@link LoyaltyPointTransaction}
 * et les DTOs de points de fidélité.
 */
@Component
public class LoyaltyPointMapper {

    /**
     * Convertit une entité {@link LoyaltyPointTransaction} en {@link LoyaltyPointDTO}.
     */
    public LoyaltyPointDTO toDTO(LoyaltyPointTransaction transaction) {
        if (transaction == null) {
            return null;
        }

        return LoyaltyPointDTO.builder()
                .userId(transaction.getUser() != null ? transaction.getUser().getId() : null)
                .deltaPoints(transaction.getPoints())
                .totalPoints(0) // Calculé par le service
                .reason(transaction.getDescription())
                .createdAt(transaction.getTransactionDate())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link LoyaltyPointTransaction} en liste de {@link LoyaltyPointDTO}.
     */
    public List<LoyaltyPointDTO> toDTOList(List<LoyaltyPointTransaction> transactions) {
        if (transactions == null) {
            return null;
        }

        return transactions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
