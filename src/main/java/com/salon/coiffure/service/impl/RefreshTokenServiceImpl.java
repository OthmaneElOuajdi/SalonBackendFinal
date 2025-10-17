package com.salon.coiffure.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.dto.auth.AuthResponse;
import com.salon.coiffure.dto.auth.RefreshTokenRequest;
import com.salon.coiffure.entity.RefreshToken;
import com.salon.coiffure.entity.User;
import com.salon.coiffure.repository.RefreshTokenRepository;
import com.salon.coiffure.repository.UserRepository;
import com.salon.coiffure.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

/**
 * Implémentation du service de gestion des jetons de rafraîchissement (refresh tokens).
 * Gère la création, la validation, la révocation et le renouvellement des jetons.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final UserRepository userRepository;

    @Override
    public RefreshToken create(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + userId));

        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusDays(7))
                .revoked(false)
                .build();

        return repo.save(token);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repo.findByToken(token);
    }

    @Override
    public void revokeByUserId(Long userId) {
        repo.deleteByUserId(userId);
    }

    /**
     * Rafraîchit un jeton d'accès en utilisant un jeton de rafraîchissement.
     * Implémente une stratégie de rotation de jeton : l'ancien jeton de rafraîchissement est révoqué
     * et un nouveau est émis avec le nouveau jeton d'accès.
     * NOTE : La génération du jeton d'accès (JWT) est actuellement simulée (`mock`).
     *
     * @param request La demande contenant le jeton de rafraîchissement à utiliser
     * @return Une nouvelle réponse d'authentification avec un nouveau jeton d'accès et un nouveau refresh token
     * @throws IllegalArgumentException si le jeton de rafraîchissement est invalide, expiré ou révoqué
     */
    @Override
    public AuthResponse refresh(RefreshTokenRequest request) {
        RefreshToken existing = repo.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new IllegalArgumentException("Refresh token invalide"));

        if (existing.isRevoked() || existing.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Refresh token expiré ou révoqué");
        }

        String newAccessToken = "mock-jwt-for-user-" + existing.getUser().getId();
        long expiresInSeconds = 3600L;

        existing.setRevoked(true);
        repo.save(existing);

        RefreshToken newToken = RefreshToken.builder()
                .user(existing.getUser())
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusDays(7))
                .revoked(false)
                .build();
        repo.save(newToken);

        return AuthResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newToken.getToken())
                .expiresIn(expiresInSeconds)
                .build();
    }
}
