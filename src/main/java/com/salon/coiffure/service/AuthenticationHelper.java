package com.salon.coiffure.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.salon.coiffure.entity.User;
import com.salon.coiffure.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Helper pour les opérations d'authentification.
 * Facilite la récupération de l'utilisateur authentifié.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationHelper {

    private final UserRepository userRepository;

    /**
     * Récupère l'utilisateur actuellement authentifié.
     *
     * @param authentication L'objet Authentication de Spring Security
     * @return L'utilisateur authentifié
     * @throws IllegalArgumentException si l'utilisateur n'est pas trouvé
     */
    public User getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("Aucun utilisateur authentifié");
        }

        final String email;
        
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            email = (String) principal;
        } else {
            throw new IllegalArgumentException("Impossible de récupérer l'email de l'utilisateur");
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable: " + email));
    }

    /**
     * Récupère l'ID de l'utilisateur actuellement authentifié.
     *
     * @param authentication L'objet Authentication de Spring Security
     * @return L'ID de l'utilisateur
     */
    public Long getCurrentUserId(Authentication authentication) {
        return getCurrentUser(authentication).getId();
    }

    /**
     * Récupère l'email de l'utilisateur actuellement authentifié.
     *
     * @param authentication L'objet Authentication de Spring Security
     * @return L'email de l'utilisateur
     */
    public String getCurrentUserEmail(Authentication authentication) {
        return getCurrentUser(authentication).getEmail();
    }

    /**
     * Vérifie si l'utilisateur authentifié a un rôle spécifique.
     *
     * @param authentication L'objet Authentication de Spring Security
     * @param roleName Le nom du rôle à vérifier
     * @return true si l'utilisateur a le rôle, false sinon
     */
    public boolean hasRole(Authentication authentication, String roleName) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(roleName));
    }
}
