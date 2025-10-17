package com.salon.coiffure.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.dto.rendezvous.RendezVousCreateDTO;
import com.salon.coiffure.dto.rendezvous.RendezVousResponseDTO;
import com.salon.coiffure.entity.Coiffeur;
import com.salon.coiffure.entity.RendezVous;
import com.salon.coiffure.entity.RendezVousStatus;
import com.salon.coiffure.entity.User;
import com.salon.coiffure.mapper.RendezVousMapper;
import com.salon.coiffure.repository.CoiffeurRepository;
import com.salon.coiffure.repository.RendezVousRepository;
import com.salon.coiffure.repository.ServiceRepository;
import com.salon.coiffure.service.AuthenticationHelper;
import com.salon.coiffure.service.RendezVousService;

import lombok.RequiredArgsConstructor;

/**
 * Implémentation du service pour la gestion des rendez-vous.
 * Fournit la logique métier pour créer, rechercher, mettre à jour et supprimer des rendez-vous.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final AuthenticationHelper authenticationHelper;
    private final ServiceRepository serviceRepository;
    private final CoiffeurRepository coiffeurRepository;
    private final RendezVousMapper rendezVousMapper;

    @Override
    public RendezVous create(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RendezVous> findById(Long id) {
        return rendezVousRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RendezVous> findByUser(Long userId) {
        return rendezVousRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RendezVous> findBetween(LocalDateTime start, LocalDateTime end) {
        return rendezVousRepository.findByStartTimeBetween(start, end);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RendezVous> findByStatus(RendezVousStatus status) {
        return rendezVousRepository.findByStatus(status);
    }

    @Override
    public RendezVous updateStatus(Long id, RendezVousStatus status) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID: " + id));
        
        rendezVous.setStatus(status);
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public void deleteById(Long id) {
        rendezVousRepository.deleteById(id);
    }

    @Override
    public RendezVousResponseDTO createRendezVous(RendezVousCreateDTO dto, Authentication authentication) {
        User user = authenticationHelper.getCurrentUser(authentication);
        
        com.salon.coiffure.entity.Service service = serviceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service non trouvé avec l'ID: " + dto.getServiceId()));
        
        Coiffeur coiffeur = null;
        if (dto.getCoiffeurId() != null) {
            coiffeur = coiffeurRepository.findById(dto.getCoiffeurId())
                    .orElseThrow(() -> new IllegalArgumentException("Coiffeur non trouvé avec l'ID: " + dto.getCoiffeurId()));
        }
        
        RendezVous rendezVous = RendezVous.builder()
                .user(user)
                .service(service)
                .coiffeur(coiffeur)
                .startTime(dto.getStartTime())
                .status(RendezVousStatus.PENDING)
                .notes(dto.getNotes())
                .build();
        
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.toResponseDTO(savedRendezVous);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RendezVousResponseDTO> getMyRendezVous(Authentication authentication) {
        User user = authenticationHelper.getCurrentUser(authentication);
        List<RendezVous> rendezVousList = rendezVousRepository.findByUserId(user.getId());
        return rendezVousMapper.toResponseDTOList(rendezVousList);
    }

    @Override
    public RendezVousResponseDTO cancelRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID: " + id));
        
        rendezVous.setStatus(RendezVousStatus.CANCELED);
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        
        return rendezVousMapper.toResponseDTO(savedRendezVous);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RendezVous> findByCoiffeur(Long coiffeurId) {
        return rendezVousRepository.findByCoiffeurId(coiffeurId);
    }

    @Override
    public RendezVous update(RendezVous rendezVous) {
        if (rendezVous.getId() == null) {
            throw new IllegalArgumentException("L'ID du rendez-vous ne peut pas être null pour une mise à jour");
        }
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous confirm(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID: " + id));
        
        rendezVous.setStatus(RendezVousStatus.CONFIRMED);
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous cancel(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID: " + id));
        
        rendezVous.setStatus(RendezVousStatus.CANCELED);
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous complete(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous introuvable avec l'ID: " + id));
        
        rendezVous.setStatus(RendezVousStatus.COMPLETED);
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isSlotAvailable(Long coiffeurId, LocalDateTime startTime) {
        List<RendezVous> existingRendezVous = rendezVousRepository.findByCoiffeurIdAndStartTime(coiffeurId, startTime);
        return existingRendezVous.isEmpty();
    }
}
