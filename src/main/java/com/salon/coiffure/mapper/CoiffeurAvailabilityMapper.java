package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.availability.CoiffeurAvailabilityCreateDTO;
import com.salon.coiffure.dto.availability.CoiffeurAvailabilityDTO;
import com.salon.coiffure.entity.CoiffeurAvailability;

/**
 * Mapper responsable de la conversion entre les entités {@link CoiffeurAvailability}
 * et les différents DTOs de disponibilité.
 */
@Component
public class CoiffeurAvailabilityMapper {

    /**
     * Convertit une entité {@link CoiffeurAvailability} en {@link CoiffeurAvailabilityDTO}.
     */
    public CoiffeurAvailabilityDTO toDTO(CoiffeurAvailability availability) {
        if (availability == null) {
            return null;
        }

        return CoiffeurAvailabilityDTO.builder()
                .id(availability.getId())
                .coiffeurId(availability.getCoiffeur() != null ? availability.getCoiffeur().getId() : null)
                .coiffeurName(availability.getCoiffeur() != null ? availability.getCoiffeur().getProfessionalName() : null)
                .startAt(availability.getStartAt())
                .endAt(availability.getEndAt())
                .type(availability.getType() != null ? availability.getType().name() : null)
                .reason(availability.getReason())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link CoiffeurAvailability} en liste de {@link CoiffeurAvailabilityDTO}.
     */
    public List<CoiffeurAvailabilityDTO> toDTOList(List<CoiffeurAvailability> availabilities) {
        if (availabilities == null) {
            return null;
        }

        return availabilities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link CoiffeurAvailabilityCreateDTO} en entité {@link CoiffeurAvailability}.
     */
    public CoiffeurAvailability toEntity(CoiffeurAvailabilityCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return CoiffeurAvailability.builder()
                .startAt(dto.getStartAt())
                .endAt(dto.getEndAt())
                .type(dto.getType())
                .reason(dto.getReason())
                .build();
    }
}
