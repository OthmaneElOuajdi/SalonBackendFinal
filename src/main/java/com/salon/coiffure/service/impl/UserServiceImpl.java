package com.salon.coiffure.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.salon.coiffure.dto.auth.AuthRequest;
import com.salon.coiffure.dto.auth.AuthResponse;
import com.salon.coiffure.entity.ERole;
import com.salon.coiffure.entity.Role;
import com.salon.coiffure.entity.User;
import com.salon.coiffure.repository.RoleRepository;
import com.salon.coiffure.repository.UserRepository;
import com.salon.coiffure.security.JwtTokenProvider;
import com.salon.coiffure.security.LoginAttemptService;
import com.salon.coiffure.service.RefreshTokenService;
import com.salon.coiffure.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Implémentation du service de gestion des utilisateurs.
 * Gère l'authentification, l'inscription, et les opérations CRUD pour les utilisateurs.
 * Intègre la gestion des tokens JWT, la protection contre les attaques par force brute et la mise en cache.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final LoginAttemptService loginAttemptService;

    @Override
    @Transactional
    public AuthResponse authenticate(AuthRequest request) {
        String clientIp = getClientIpAddress();

        if (loginAttemptService.isBlocked(clientIp)) {
            long remainingMinutes = loginAttemptService.getRemainingLockoutMinutes(clientIp);
            throw new IllegalArgumentException(
                    String.format("Trop de tentatives de connexion échouées. Compte bloqué pendant %d minutes.",
                            remainingMinutes));
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));

            loginAttemptService.recordSuccessfulAttempt(clientIp);

            user.setLastLoginAt(LocalDateTime.now());
            userRepository.save(user);

            String accessToken = jwtTokenProvider.generateToken(authentication);
            String refreshToken = refreshTokenService.create(user.getId()).getToken();

            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expiresIn(3600L)
                    .build();
        } catch (BadCredentialsException e) {
            loginAttemptService.recordFailedAttempt(clientIp);

            int remainingAttempts = loginAttemptService.getRemainingAttempts(clientIp);
            if (remainingAttempts > 0) {
                throw new IllegalArgumentException(
                        String.format("Identifiants invalides. %d tentatives restantes.", remainingAttempts));
            } else {
                throw new IllegalArgumentException("Trop de tentatives échouées. Compte temporairement bloqué.");
            }
        } catch (Exception e) {
            loginAttemptService.recordFailedAttempt(clientIp);
            throw new IllegalArgumentException("Erreur d'authentification");
        }
    }

    /**
     * Récupère l'adresse IP du client.
     */
    private String getClientIpAddress() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String xForwardedFor = request.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
                return xForwardedFor.split(",")[0].trim();
            }

            String xRealIp = request.getHeader("X-Real-IP");
            if (xRealIp != null && !xRealIp.isEmpty()) {
                return xRealIp;
            }

            return request.getRemoteAddr();
        }

        return "unknown";
    }

    @Override
    @Transactional
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                .orElseThrow(() -> new IllegalStateException("Rôle ROLE_CLIENT non trouvé dans la base de données"));
        user.getRoles().add(clientRole);

        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#id")
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "#email")
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "users", key = "'all'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User update(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            userRepository.findById(user.getId())
                    .ifPresent(existingUser -> user.setPassword(existingUser.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUserFromToken(String token) {
        String email = jwtTokenProvider.getUsernameFromToken(token);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable pour le token fourni"));
    }
}
