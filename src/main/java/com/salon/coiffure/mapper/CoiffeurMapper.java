package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.coiffeur.CoiffeurCreateDTO;
import com.salon.coiffure.dto.coiffeur.CoiffeurDTO;
import com.salon.coiffure.dto.coiffeur.CoiffeurResponseDTO;
import com.salon.coiffure.dto.coiffeur.CoiffeurUpdateDTO;
import com.salon.coiffure.entity.Coiffeur;

/**
 * Mapper responsable de la conversion entre les entités {@link Coiffeur}
 * et les différents DTOs de coiffeur.
 */
@Component
public class CoiffeurMapper {

    /**
     * Convertit une entité {@link Coiffeur} en {@link CoiffeurDTO}.
     */
    public CoiffeurDTO toDTO(Coiffeur coiffeur) {
        if (coiffeur == null) {
            return null;
        }

        return CoiffeurDTO.builder()
                .id(coiffeur.getId())
                .userId(coiffeur.getUser() != null ? coiffeur.getUser().getId() : null)
                .professionalName(coiffeur.getProfessionalName())
                .specialties(coiffeur.getSpecialties())
                .bio(coiffeur.getBio())
                .photoUrl(coiffeur.getPhotoUrl())
                .active(coiffeur.isActive())
                .averageRating(0.0) // Calculé par le service
                .reviewCount(0L) // Calculé par le service
                .createdAt(coiffeur.getCreatedAt())
                .build();
    }

    /**
     * Convertit une entité {@link Coiffeur} en {@link CoiffeurResponseDTO}.
     */
    public CoiffeurResponseDTO toResponseDTO(Coiffeur coiffeur) {
        if (coiffeur == null) {
            return null;
        }

        return CoiffeurResponseDTO.builder()
                .id(coiffeur.getId())
                .userId(coiffeur.getUser() != null ? coiffeur.getUser().getId() : null)
                .userEmail(coiffeur.getUser() != null ? coiffeur.getUser().getEmail() : null)
                .professionalName(coiffeur.getProfessionalName())
                .specialties(coiffeur.getSpecialties())
                .bio(coiffeur.getBio())
                .photoUrl(coiffeur.getPhotoUrl())
                .active(coiffeur.isActive())
                .averageRating(0.0) // Calculé par le service
                .reviewCount(0L) // Calculé par le service
                .createdAt(coiffeur.getCreatedAt())
                .updatedAt(coiffeur.getUpdatedAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link Coiffeur} en liste de {@link CoiffeurResponseDTO}.
     */
    public List<CoiffeurResponseDTO> toResponseDTOList(List<Coiffeur> coiffeurs) {
        if (coiffeurs == null) {
            return null;
        }

        return coiffeurs.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link CoiffeurCreateDTO} en entité {@link Coiffeur}.
     */
    public Coiffeur toEntity(CoiffeurCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Coiffeur.builder()
                .professionalName(dto.getProfessionalName())
                .specialties(dto.getSpecialties())
                .bio(dto.getBio())
                .photoUrl(dto.getPhotoUrl())
                .build();
    }

    /**
     * Met à jour une entité {@link Coiffeur} avec les données d'un {@link CoiffeurUpdateDTO}.
     */
    public void updateEntityFromDTO(Coiffeur coiffeur, CoiffeurUpdateDTO dto) {
        if (coiffeur == null || dto == null) {
            return;
        }

        if (dto.getProfessionalName() != null) {
            coiffeur.setProfessionalName(dto.getProfessionalName());
        }
        if (dto.getSpecialties() != null) {
            coiffeur.setSpecialties(dto.getSpecialties());
        }
        if (dto.getBio() != null) {
            coiffeur.setBio(dto.getBio());
        }
        if (dto.getPhotoUrl() != null) {
            coiffeur.setPhotoUrl(dto.getPhotoUrl());
        }
        if (dto.getActive() != null) {
            coiffeur.setActive(dto.getActive());
        }
    }
}
