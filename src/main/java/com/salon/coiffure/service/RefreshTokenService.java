package com.salon.coiffure.service;

import java.util.Optional;

import com.salon.coiffure.dto.auth.AuthResponse;
import com.salon.coiffure.dto.auth.RefreshTokenRequest;
import com.salon.coiffure.entity.RefreshToken;

/**
 * Interface du service de gestion des jetons de rafraîchissement (refresh tokens).
 * Gère la création, la validation, la révocation et le renouvellement des jetons.
 */
public interface RefreshTokenService {

    /**
     * Crée un nouveau refresh token pour un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @return Le refresh token créé
     */
    RefreshToken create(Long userId);

    /**
     * Recherche un refresh token par sa valeur.
     *
     * @param token La valeur du token
     * @return Un Optional contenant le token si trouvé
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Révoque tous les tokens d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     */
    void revokeByUserId(Long userId);

    /**
     * Rafraîchit un jeton d'accès en utilisant un refresh token.
     * Implémente une stratégie de rotation de jeton.
     *
     * @param request La demande contenant le refresh token
     * @return Une nouvelle réponse d'authentification
     * @throws IllegalArgumentException si le token est invalide, expiré ou révoqué
     */
    AuthResponse refresh(RefreshTokenRequest request);
}
