package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.role.RoleDTO;
import com.salon.coiffure.entity.Role;

/**
 * Mapper responsable de la conversion entre les entités {@link Role}
 * et les DTOs de rôle.
 */
@Component
public class RoleMapper {

    /**
     * Convertit une entité {@link Role} en {@link RoleDTO}.
     */
    public RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName() != null ? role.getName().name() : null)
                .description("") // L'entité Role n'a pas de description
                .build();
    }

    /**
     * Convertit une liste d'entités {@link Role} en liste de {@link RoleDTO}.
     */
    public List<RoleDTO> toDTOList(List<Role> roles) {
        if (roles == null) {
            return null;
        }

        return roles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
