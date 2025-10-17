package com.salon.coiffure.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.LoyaltyPointTransaction;
import com.salon.coiffure.entity.User;
import com.salon.coiffure.repository.LoyaltyPointTransactionRepository;
import com.salon.coiffure.repository.UserRepository;
import com.salon.coiffure.service.LoyaltyPointService;

import lombok.RequiredArgsConstructor;

/**
 * Implémentation du service de gestion des points de fidélité.
 * Gère l'attribution, l'utilisation et la consultation des points pour les utilisateurs.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LoyaltyPointServiceImpl implements LoyaltyPointService {

    private final LoyaltyPointTransactionRepository loyaltyPointTransactionRepository;
    private final UserRepository userRepository;

    @Override
    public LoyaltyPointTransaction awardPoints(Long userId, int points, String reason) {
        return createTransaction(userId, points, reason);
    }

    @Override
    public LoyaltyPointTransaction redeemPoints(Long userId, int points, String reason) {
        return createTransaction(userId, -Math.abs(points), reason); // on soustrait les points
    }

    @Override
    public int getTotalPoints(Long userId) {
        return loyaltyPointTransactionRepository.findByUserIdOrderByTransactionDateDesc(userId)
                .stream()
                .mapToInt(LoyaltyPointTransaction::getPoints)
                .sum();
    }

    @Override
    public List<LoyaltyPointTransaction> history(Long userId) {
        return loyaltyPointTransactionRepository.findByUserIdOrderByTransactionDateDesc(userId);
    }

    /**
     * Méthode privée pour créer et sauvegarder une transaction de points de fidélité.
     * Gère à la fois l'ajout (points positifs) et le retrait (points négatifs).
     *
     * @param userId L'ID de l'utilisateur concerné
     * @param points Le nombre de points à ajouter ou retirer
     * @param description La raison de la transaction
     * @return La transaction de points de fidélité créée
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    private LoyaltyPointTransaction createTransaction(Long userId, int points, String description) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID: " + userId));

        LoyaltyPointTransaction transaction = LoyaltyPointTransaction.builder()
                .user(user)
                .points(points)
                .description(description)
                .build();

        return loyaltyPointTransactionRepository.save(transaction);
    }
}
