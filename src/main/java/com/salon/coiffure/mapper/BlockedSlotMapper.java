package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.blockedslot.BlockedSlotCreateDTO;
import com.salon.coiffure.dto.blockedslot.BlockedSlotDTO;
import com.salon.coiffure.entity.BlockedSlot;

/**
 * Mapper responsable de la conversion entre les entités {@link BlockedSlot}
 * et les différents DTOs de créneau bloqué.
 */
@Component
public class BlockedSlotMapper {

    /**
     * Convertit une entité {@link BlockedSlot} en {@link BlockedSlotDTO}.
     */
    public BlockedSlotDTO toDTO(BlockedSlot blockedSlot) {
        if (blockedSlot == null) {
            return null;
        }

        return BlockedSlotDTO.builder()
                .id(blockedSlot.getId())
                .startAt(blockedSlot.getStartAt())
                .endAt(blockedSlot.getEndAt())
                .reason(blockedSlot.getReason())
                .createdById(blockedSlot.getCreatedBy() != null ? blockedSlot.getCreatedBy().getId() : null)
                .createdByName(blockedSlot.getCreatedBy() != null ? blockedSlot.getCreatedBy().getFullName() : null)
                .createdAt(blockedSlot.getCreatedAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link BlockedSlot} en liste de {@link BlockedSlotDTO}.
     */
    public List<BlockedSlotDTO> toDTOList(List<BlockedSlot> blockedSlots) {
        if (blockedSlots == null) {
            return null;
        }

        return blockedSlots.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link BlockedSlotCreateDTO} en entité {@link BlockedSlot}.
     */
    public BlockedSlot toEntity(BlockedSlotCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return BlockedSlot.builder()
                .startAt(dto.getStartAt())
                .endAt(dto.getEndAt())
                .reason(dto.getReason())
                .build();
    }
}
