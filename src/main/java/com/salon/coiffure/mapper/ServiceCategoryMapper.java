package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.category.ServiceCategoryCreateDTO;
import com.salon.coiffure.dto.category.ServiceCategoryDTO;
import com.salon.coiffure.dto.category.ServiceCategoryUpdateDTO;
import com.salon.coiffure.entity.ServiceCategory;

/**
 * Mapper responsable de la conversion entre les entités {@link ServiceCategory}
 * et les différents DTOs de catégorie de service.
 */
@Component
public class ServiceCategoryMapper {

    /**
     * Convertit une entité {@link ServiceCategory} en {@link ServiceCategoryDTO}.
     */
    public ServiceCategoryDTO toDTO(ServiceCategory category) {
        if (category == null) {
            return null;
        }

        return ServiceCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .displayOrder(category.getDisplayOrder())
                .active(category.isActive())
                .serviceCount(category.getServices() != null ? category.getServices().size() : 0)
                .build();
    }

    /**
     * Convertit une liste d'entités {@link ServiceCategory} en liste de {@link ServiceCategoryDTO}.
     */
    public List<ServiceCategoryDTO> toDTOList(List<ServiceCategory> categories) {
        if (categories == null) {
            return null;
        }

        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link ServiceCategoryCreateDTO} en entité {@link ServiceCategory}.
     */
    public ServiceCategory toEntity(ServiceCategoryCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return ServiceCategory.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .displayOrder(dto.getDisplayOrder())
                .build();
    }

    /**
     * Met à jour une entité {@link ServiceCategory} avec les données d'un {@link ServiceCategoryUpdateDTO}.
     */
    public void updateEntityFromDTO(ServiceCategory category, ServiceCategoryUpdateDTO dto) {
        if (category == null || dto == null) {
            return;
        }

        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
        if (dto.getDisplayOrder() != null) {
            category.setDisplayOrder(dto.getDisplayOrder());
        }
        if (dto.getActive() != null) {
            category.setActive(dto.getActive());
        }
    }
}
