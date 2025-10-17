package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.workinghours.WorkingHoursDTO;
import com.salon.coiffure.dto.workinghours.WorkingHoursUpdateDTO;
import com.salon.coiffure.entity.WorkingHours;

/**
 * Mapper responsable de la conversion entre les entités {@link WorkingHours}
 * et les différents DTOs d'horaires de travail.
 */
@Component
public class WorkingHoursMapper {

    /**
     * Convertit une entité {@link WorkingHours} en {@link WorkingHoursDTO}.
     */
    public WorkingHoursDTO toDTO(WorkingHours workingHours) {
        if (workingHours == null) {
            return null;
        }

        return WorkingHoursDTO.builder()
                .id(workingHours.getId())
                .dayOfWeek(workingHours.getDayOfWeek() != null ? workingHours.getDayOfWeek().name() : null)
                .startTime(workingHours.getStartTime())
                .endTime(workingHours.getEndTime())
                .closed(workingHours.isClosed())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link WorkingHours} en liste de {@link WorkingHoursDTO}.
     */
    public List<WorkingHoursDTO> toDTOList(List<WorkingHours> workingHoursList) {
        if (workingHoursList == null) {
            return null;
        }

        return workingHoursList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link WorkingHoursUpdateDTO} en entité {@link WorkingHours}.
     */
    public WorkingHours toEntity(WorkingHoursUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        return WorkingHours.builder()
                .dayOfWeek(dto.getDayOfWeek())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .closed(dto.getClosed() != null ? dto.getClosed() : false)
                .build();
    }

    /**
     * Met à jour une entité {@link WorkingHours} avec les données d'un {@link WorkingHoursUpdateDTO}.
     */
    public void updateEntityFromDTO(WorkingHours workingHours, WorkingHoursUpdateDTO dto) {
        if (workingHours == null || dto == null) {
            return;
        }

        if (dto.getDayOfWeek() != null) {
            workingHours.setDayOfWeek(dto.getDayOfWeek());
        }
        if (dto.getStartTime() != null) {
            workingHours.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            workingHours.setEndTime(dto.getEndTime());
        }
        if (dto.getClosed() != null) {
            workingHours.setClosed(dto.getClosed());
        }
    }
}
