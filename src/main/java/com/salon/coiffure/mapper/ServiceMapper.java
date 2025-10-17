package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.service.ServiceCreateDTO;
import com.salon.coiffure.dto.service.ServiceDTO;
import com.salon.coiffure.dto.service.ServiceResponseDTO;
import com.salon.coiffure.dto.service.ServiceUpdateDTO;
import com.salon.coiffure.entity.Service;

/**
 * Mapper responsable de la conversion entre les entités {@link Service}
 * et les différents DTOs de service.
 */
@Component
public class ServiceMapper {

    /**
     * Convertit une entité {@link Service} en {@link ServiceDTO}.
     */
    public ServiceDTO toDTO(Service service) {
        if (service == null) {
            return null;
        }

        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .price(java.math.BigDecimal.valueOf(service.getPrice()))
                .duration(service.getDuration())
                .loyaltyPointsReward(service.getLoyaltyPointsReward())
                .active(service.isActive())
                .categoryId(service.getCategory() != null ? service.getCategory().getId() : null)
                .categoryName(service.getCategory() != null ? service.getCategory().getName() : null)
                .build();
    }

    /**
     * Convertit une entité {@link Service} en {@link ServiceResponseDTO}.
     */
    public ServiceResponseDTO toResponseDTO(Service service) {
        if (service == null) {
            return null;
        }

        return ServiceResponseDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .price(java.math.BigDecimal.valueOf(service.getPrice()))
                .duration(service.getDuration())
                .loyaltyPointsReward(service.getLoyaltyPointsReward())
                .active(service.isActive())
                .categoryId(service.getCategory() != null ? service.getCategory().getId() : null)
                .categoryName(service.getCategory() != null ? service.getCategory().getName() : null)
                .createdAt(null) // Service n'a pas de createdAt
                .updatedAt(null) // Service n'a pas de updatedAt
                .build();
    }

    /**
     * Convertit une liste d'entités {@link Service} en liste de {@link ServiceResponseDTO}.
     */
    public List<ServiceResponseDTO> toResponseDTOList(List<Service> services) {
        if (services == null) {
            return null;
        }

        return services.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link ServiceCreateDTO} en entité {@link Service}.
     */
    public Service toEntity(ServiceCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Service.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice().doubleValue())
                .duration(dto.getDuration())
                .loyaltyPointsReward(dto.getLoyaltyPointsReward())
                .active(dto.getActive())
                .build();
    }

    /**
     * Met à jour une entité {@link Service} avec les données d'un {@link ServiceUpdateDTO}.
     */
    public void updateEntityFromDTO(Service service, ServiceUpdateDTO dto) {
        if (service == null || dto == null) {
            return;
        }

        if (dto.getName() != null) {
            service.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            service.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            service.setPrice(dto.getPrice().doubleValue());
        }
        if (dto.getDuration() != null) {
            service.setDuration(dto.getDuration());
        }
        if (dto.getLoyaltyPointsReward() != null) {
            service.setLoyaltyPointsReward(dto.getLoyaltyPointsReward());
        }
        if (dto.getActive() != null) {
            service.setActive(dto.getActive());
        }
    }
}
