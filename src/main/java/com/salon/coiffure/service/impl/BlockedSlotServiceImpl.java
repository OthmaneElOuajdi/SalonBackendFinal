package com.salon.coiffure.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.BlockedSlot;
import com.salon.coiffure.repository.BlockedSlotRepository;
import com.salon.coiffure.service.BlockedSlotService;

import lombok.RequiredArgsConstructor;

/**
 * Implémentation du service pour la gestion des créneaux horaires bloqués.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BlockedSlotServiceImpl implements BlockedSlotService {

    private final BlockedSlotRepository repo;

    @Override
    public BlockedSlot create(BlockedSlot slot) {
        return repo.save(slot);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<BlockedSlot> findBetween(LocalDateTime start, LocalDateTime end) {
        return repo.findByStartAtLessThanEqualAndEndAtGreaterThanEqual(end, start);
    }
}
