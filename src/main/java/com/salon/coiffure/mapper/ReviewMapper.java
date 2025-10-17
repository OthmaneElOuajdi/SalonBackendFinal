package com.salon.coiffure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salon.coiffure.dto.review.ReviewCreateDTO;
import com.salon.coiffure.dto.review.ReviewDTO;
import com.salon.coiffure.dto.review.ReviewResponseDTO;
import com.salon.coiffure.entity.Review;

/**
 * Mapper responsable de la conversion entre les entités {@link Review}
 * et les différents DTOs d'avis.
 */
@Component
public class ReviewMapper {

    /**
     * Convertit une entité {@link Review} en {@link ReviewDTO}.
     */
    public ReviewDTO toDTO(Review review) {
        if (review == null) {
            return null;
        }

        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .userName(review.getUser() != null ? review.getUser().getFullName() : null)
                .coiffeurId(review.getCoiffeur() != null ? review.getCoiffeur().getId() : null)
                .coiffeurName(review.getCoiffeur() != null ? review.getCoiffeur().getProfessionalName() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .approved(review.isApproved())
                .createdAt(review.getCreatedAt())
                .build();
    }

    /**
     * Convertit une entité {@link Review} en {@link ReviewResponseDTO}.
     */
    public ReviewResponseDTO toResponseDTO(Review review) {
        if (review == null) {
            return null;
        }

        return ReviewResponseDTO.builder()
                .id(review.getId())
                .userId(review.getUser() != null ? review.getUser().getId() : null)
                .userName(review.getUser() != null ? review.getUser().getFullName() : null)
                .coiffeurId(review.getCoiffeur() != null ? review.getCoiffeur().getId() : null)
                .coiffeurName(review.getCoiffeur() != null ? review.getCoiffeur().getProfessionalName() : null)
                .rendezVousId(review.getRendezVous() != null ? review.getRendezVous().getId() : null)
                .rating(review.getRating())
                .comment(review.getComment())
                .approved(review.isApproved())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    /**
     * Convertit une liste d'entités {@link Review} en liste de {@link ReviewResponseDTO}.
     */
    public List<ReviewResponseDTO> toResponseDTOList(List<Review> reviews) {
        if (reviews == null) {
            return null;
        }

        return reviews.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convertit un {@link ReviewCreateDTO} en entité {@link Review}.
     */
    public Review toEntity(ReviewCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return Review.builder()
                .rating(dto.getRating())
                .comment(dto.getComment())
                .approved(false) // Par défaut, les avis doivent être approuvés
                .build();
    }
}
