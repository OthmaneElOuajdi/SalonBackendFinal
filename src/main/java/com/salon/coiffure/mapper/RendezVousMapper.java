package com.salon.coiffure.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.rendezvous.RendezVousResponseDTO;
import com.salon.coiffure.entity.RendezVous;

/**
 * Mapper responsable de la conversion des entités {@link RendezVous}
 * vers des objets {@link RendezVousResponseDTO}.
 * <p>
 * Cette classe permet de séparer la logique métier (entités JPA) 
 * de la couche API (DTO exposés via les contrôleurs REST).
 */
@Component
public class RendezVousMapper {

    /**
     * Convertit une entité {@link RendezVous} en {@link RendezVousResponseDTO}.
     * <p>
     * Les informations principales du rendez-vous, ainsi que les
     * données de l'utilisateur, du service et du coiffeur associés, sont mappées
     * dans l'objet DTO. Les valeurs nulles sont gérées afin d'éviter
     * les NullPointerException.
     *
     * @param rendezVous l'entité {@link RendezVous} à convertir
     * @return une instance de {@link RendezVousResponseDTO} contenant
     *         les données du rendez-vous
     */
    public RendezVousResponseDTO toResponseDTO(RendezVous rendezVous) {
        if (rendezVous == null) {
            return null;
        }

        return RendezVousResponseDTO.builder()
                .id(rendezVous.getId())
                .startTime(rendezVous.getStartTime())
                .status(rendezVous.getStatus())
                .notes(rendezVous.getNotes())
                .userId(rendezVous.getUser() != null ? rendezVous.getUser().getId() : null)
                .userName(rendezVous.getUser() != null ? rendezVous.getUser().getFullName() : null)
                .serviceId(rendezVous.getService() != null ? rendezVous.getService().getId() : null)
                .serviceName(rendezVous.getService() != null ? rendezVous.getService().getName() : null)
                .servicePrice(rendezVous.getService() != null ? BigDecimal.valueOf(rendezVous.getService().getPrice()) : null)
                .serviceDuration(rendezVous.getService() != null ? rendezVous.getService().getDuration() : null)
                .coiffeurId(rendezVous.getCoiffeur() != null ? rendezVous.getCoiffeur().getId() : null)
                .coiffeurName(rendezVous.getCoiffeur() != null ? rendezVous.getCoiffeur().getProfessionalName() : null)
                .createdAt(rendezVous.getCreatedAt())
                .updatedAt(rendezVous.getUpdatedAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link RendezVous} en une liste
     * de {@link RendezVousResponseDTO}.
     *
     * @param rendezVousList la liste d'entités {@link RendezVous} à convertir
     * @return une liste de {@link RendezVousResponseDTO}
     */
    public List<RendezVousResponseDTO> toResponseDTOList(List<RendezVous> rendezVousList) {
        if (rendezVousList == null) {
            return null;
        }

        return rendezVousList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }
}
