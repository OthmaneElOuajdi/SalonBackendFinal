package com.salon.coiffure.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.salon.coiffure.dto.rendezvous.RendezVousCreateDTO;
import com.salon.coiffure.dto.rendezvous.RendezVousResponseDTO;
import com.salon.coiffure.entity.RendezVous;
import com.salon.coiffure.entity.RendezVousStatus;

/**
 * Interface du service pour la gestion des rendez-vous.
 * Fournit la logique métier pour créer, rechercher, mettre à jour et supprimer des rendez-vous.
 */
public interface RendezVousService {

    /**
     * Crée un nouveau rendez-vous.
     *
     * @param rendezVous Le rendez-vous à créer
     * @return Le rendez-vous créé
     */
    RendezVous create(RendezVous rendezVous);

    /**
     * Recherche tous les rendez-vous.
     *
     * @return Liste de tous les rendez-vous
     */
    List<RendezVous> findAll();

    /**
     * Recherche un rendez-vous par son ID.
     *
     * @param id L'ID du rendez-vous
     * @return Un Optional contenant le rendez-vous si trouvé
     */
    Optional<RendezVous> findById(Long id);

    /**
     * Recherche les rendez-vous d'un utilisateur.
     *
     * @param userId L'ID de l'utilisateur
     * @return Liste des rendez-vous de l'utilisateur
     */
    List<RendezVous> findByUser(Long userId);

    /**
     * Recherche les rendez-vous d'un coiffeur.
     *
     * @param coiffeurId L'ID du coiffeur
     * @return Liste des rendez-vous du coiffeur
     */
    List<RendezVous> findByCoiffeur(Long coiffeurId);

    /**
     * Recherche les rendez-vous entre deux dates.
     *
     * @param start Date de début
     * @param end Date de fin
     * @return Liste des rendez-vous dans la période
     */
    List<RendezVous> findBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Recherche les rendez-vous par statut.
     *
     * @param status Le statut du rendez-vous
     * @return Liste des rendez-vous avec ce statut
     */
    List<RendezVous> findByStatus(RendezVousStatus status);

    /**
     * Met à jour un rendez-vous.
     *
     * @param rendezVous Le rendez-vous à mettre à jour
     * @return Le rendez-vous mis à jour
     */
    RendezVous update(RendezVous rendezVous);

    /**
     * Met à jour le statut d'un rendez-vous.
     *
     * @param id L'ID du rendez-vous
     * @param status Le nouveau statut
     * @return Le rendez-vous mis à jour
     */
    RendezVous updateStatus(Long id, RendezVousStatus status);

    /**
     * Confirme un rendez-vous.
     *
     * @param id L'ID du rendez-vous
     * @return Le rendez-vous confirmé
     */
    RendezVous confirm(Long id);

    /**
     * Annule un rendez-vous.
     *
     * @param id L'ID du rendez-vous
     * @return Le rendez-vous annulé
     */
    RendezVous cancel(Long id);

    /**
     * Marque un rendez-vous comme complété.
     *
     * @param id L'ID du rendez-vous
     * @return Le rendez-vous complété
     */
    RendezVous complete(Long id);

    /**
     * Vérifie si un créneau est disponible.
     *
     * @param coiffeurId L'ID du coiffeur
     * @param startTime L'heure de début
     * @return true si disponible, false sinon
     */
    boolean isSlotAvailable(Long coiffeurId, LocalDateTime startTime);

    /**
     * Supprime un rendez-vous par son ID.
     *
     * @param id L'ID du rendez-vous
     */
    void deleteById(Long id);

    /**
     * Crée un nouveau rendez-vous pour l'utilisateur authentifié.
     *
     * @param dto DTO contenant les données du rendez-vous
     * @param authentication Authentification de l'utilisateur
     * @return Le DTO de réponse du rendez-vous créé
     */
    RendezVousResponseDTO createRendezVous(RendezVousCreateDTO dto, Authentication authentication);

    /**
     * Récupère les rendez-vous de l'utilisateur authentifié.
     *
     * @param authentication Authentification de l'utilisateur
     * @return Liste des rendez-vous de l'utilisateur
     */
    List<RendezVousResponseDTO> getMyRendezVous(Authentication authentication);

    /**
     * Annule un rendez-vous.
     *
     * @param id L'ID du rendez-vous
     * @return Le DTO de réponse du rendez-vous annulé
     */
    RendezVousResponseDTO cancelRendezVous(Long id);
}
