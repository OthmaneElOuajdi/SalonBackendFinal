package com.salon.coiffure.service;

import java.util.List;
import java.util.Optional;

import com.salon.coiffure.dto.auth.AuthRequest;
import com.salon.coiffure.dto.auth.AuthResponse;
import com.salon.coiffure.entity.User;

/**
 * Interface du service de gestion des utilisateurs.
 * Gère l'authentification, l'inscription, et les opérations CRUD pour les utilisateurs.
 */
public interface UserService {

    /**
     * Authentifie un utilisateur et retourne les jetons d'accès et de rafraîchissement.
     *
     * @param request Les informations d'authentification (email, mot de passe)
     * @return Une réponse contenant les jetons
     * @throws IllegalArgumentException si les identifiants sont invalides ou si le compte est bloqué
     */
    AuthResponse authenticate(AuthRequest request);

    /**
     * Enregistre un nouvel utilisateur avec le rôle par défaut.
     *
     * @param user L'utilisateur à enregistrer
     * @return L'utilisateur sauvegardé
     * @throws IllegalArgumentException si l'email est déjà utilisé
     */
    User register(User user);

    /**
     * Crée un nouvel utilisateur (généralement par un administrateur).
     *
     * @param user L'utilisateur à créer
     * @return L'utilisateur sauvegardé
     */
    User create(User user);

    /**
     * Recherche un utilisateur par son ID.
     *
     * @param id L'ID de l'utilisateur
     * @return Un Optional contenant l'utilisateur si trouvé
     */
    Optional<User> findById(Long id);

    /**
     * Recherche un utilisateur par son email.
     *
     * @param email L'email de l'utilisateur
     * @return Un Optional contenant l'utilisateur si trouvé
     */
    Optional<User> findByEmail(String email);

    /**
     * Récupère tous les utilisateurs.
     *
     * @return Liste de tous les utilisateurs
     */
    List<User> findAll();

    /**
     * Met à jour un utilisateur existant.
     *
     * @param user L'utilisateur avec les informations mises à jour
     * @return L'utilisateur mis à jour
     */
    User update(User user);

    /**
     * Supprime un utilisateur par son ID.
     *
     * @param id L'ID de l'utilisateur
     */
    void deleteById(Long id);

    /**
     * Récupère l'utilisateur actuellement authentifié à partir d'un token JWT.
     *
     * @param token Le token JWT (sans le préfixe 'Bearer ')
     * @return L'utilisateur correspondant au token
     * @throws IllegalArgumentException si aucun utilisateur n'est trouvé
     */
    User getCurrentUserFromToken(String token);
}
