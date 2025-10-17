package com.salon.coiffure.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.user.UserCreateDTO;
import com.salon.coiffure.dto.user.UserProfileDTO;
import com.salon.coiffure.dto.user.UserResponseDTO;
import com.salon.coiffure.dto.user.UserUpdateDTO;
import com.salon.coiffure.entity.User;

/**
 * Mapper responsable de la conversion entre les entités {@link User}
 * et les différents DTOs utilisateur.
 * <p>
 * Cette classe permet de séparer la logique métier (entités JPA) 
 * de la couche API (DTO exposés via les contrôleurs REST).
 */
@Component
public class UserMapper {

    /**
     * Convertit une entité {@link User} en {@link UserResponseDTO}.
     * <p>
     * Exclut les informations sensibles comme le mot de passe et
     * mappe les rôles en chaînes de caractères pour l'API.
     *
     * @param user l'entité {@link User} à convertir
     * @return une instance de {@link UserResponseDTO}
     */
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        // Conversion des rôles en Set<String>
        Set<String> roleNames = null;
        if (user.getRoles() != null) {
            roleNames = user.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toSet());
        }

        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .loyaltyPoints(user.getLoyaltyPoints())
                .active(user.isActive())
                .roles(roleNames)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link User} en liste de {@link UserResponseDTO}.
     *
     * @param users la liste d'entités {@link User} à convertir
     * @return une liste de {@link UserResponseDTO}
     */
    public List<UserResponseDTO> toResponseDTOList(List<User> users) {
        if (users == null) {
            return null;
        }

        return users.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link UserCreateDTO} en entité {@link User}.
     * <p>
     * Utilisé lors de la création d'un nouvel utilisateur.
     * Les rôles et autres propriétés calculées sont gérées par le service.
     *
     * @param dto le {@link UserCreateDTO} à convertir
     * @return une nouvelle entité {@link User}
     */
    public User toEntity(UserCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }

    /**
     * Met à jour une entité {@link User} existante avec les données d'un {@link UserUpdateDTO}.
     * <p>
     * Seuls les champs non-null du DTO sont appliqués à l'entité.
     * Cette méthode ne modifie pas l'ID, les rôles, ou les timestamps.
     *
     * @param existingUser l'entité {@link User} existante à mettre à jour
     * @param updateDTO le {@link UserUpdateDTO} contenant les nouvelles données
     */
    public void updateEntityFromDTO(User existingUser, UserUpdateDTO updateDTO) {
        if (existingUser == null || updateDTO == null) {
            return;
        }

        if (updateDTO.getFullName() != null) {
            existingUser.setFullName(updateDTO.getFullName());
        }
        
        if (updateDTO.getPhone() != null) {
            existingUser.setPhone(updateDTO.getPhone());
        }
        
    }

    /**
     * Convertit un {@link UserUpdateDTO} en entité {@link User} partielle.
     * <p>
     * Utilisé quand on a besoin d'une entité pour les opérations de mise à jour.
     * L'ID doit être défini séparément.
     *
     * @param dto le {@link UserUpdateDTO} à convertir
     * @return une entité {@link User} avec les champs du DTO
     */
    public User toEntityFromUpdateDTO(UserUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        User.UserBuilder builder = User.builder()
                .fullName(dto.getFullName())
                .phone(dto.getPhone());
        
        // Le mot de passe sera géré par le service si fourni
        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            builder.password(dto.getPassword());
        }

        return builder.build();
    }

    /**
     * Convertit une entité {@link User} en {@link UserProfileDTO}.
     * <p>
     * Utilisé pour l'endpoint /api/auth/me qui retourne le profil complet de l'utilisateur.
     *
     * @param user l'entité {@link User} à convertir
     * @return une instance de {@link UserProfileDTO}
     */
    public UserProfileDTO toProfileDTO(User user) {
        if (user == null) {
            return null;
        }

        // Conversion des rôles
        List<UserProfileDTO.RoleDTO> roleDTOs = null;
        if (user.getRoles() != null) {
            roleDTOs = user.getRoles().stream()
                    .map(role -> UserProfileDTO.RoleDTO.builder()
                            .id(role.getId())
                            .name(role.getName().name())
                            .build())
                    .collect(Collectors.toList());
        }

        return UserProfileDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .loyaltyPoints(user.getLoyaltyPoints())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .roles(roleDTOs)
                .build();
    }
}
