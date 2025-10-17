package com.salon.coiffure.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salon.coiffure.entity.Review;
import com.salon.coiffure.repository.ReviewRepository;
import com.salon.coiffure.service.ReviewService;
import com.salon.coiffure.service.base.BaseServiceImpl;

/**
 * Implémentation du service pour la gestion des avis clients.
 * Hérite de BaseServiceImpl pour éviter la duplication de code.
 */
@Service
@Transactional
public class ReviewServiceImpl extends BaseServiceImpl<Review, Long> implements ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * Constructeur pour l'injection du repository des avis.
     *
     * @param repository Le repository pour les entités Review
     */
    public ReviewServiceImpl(ReviewRepository repository) {
        super(repository);
        this.reviewRepository = repository;
    }

    @Override
    public List<Review> findByCoiffeur(Long coiffeurId) {
        return reviewRepository.findByCoiffeurId(coiffeurId);
    }

    @Override
    public List<Review> findApprovedByCoiffeur(Long coiffeurId) {
        return reviewRepository.findByCoiffeurIdAndApproved(coiffeurId, true);
    }

    @Override
    public List<Review> findByUser(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> findPendingReviews() {
        return reviewRepository.findByApproved(false);
    }

    @Override
    public Review approveReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis introuvable avec l'ID: " + id));
        
        review.setApproved(true);
        return reviewRepository.save(review);
    }

    @Override
    public Double getAverageRating(Long coiffeurId) {
        Double average = reviewRepository.findAverageRatingByCoiffeurId(coiffeurId);
        return average != null ? average : 0.0;
    }
}
