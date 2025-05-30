package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.ReviewDomain;
import com.shreya.hibernate.exception.*;
import com.shreya.hibernate.model.Review;
import com.shreya.hibernate.repository.ReviewRepository;
import com.shreya.hibernate.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public boolean addReview(Review review) {
        log.info("Adding review: {}", review);
        ReviewDomain domain = toDomain(review);
        reviewRepository.save(domain);
        return true;
    }

    @Override
    public List<Review> getAllReviews() {
        log.info("Fetching all reviews");
        return reviewRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Review getReviewById(Long id) {
        log.info("Getting review by ID: {}", id);
        ReviewDomain domain = reviewRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with ID: " + id));
        return toModel(domain);
    }

    @Override
    public boolean updateReview(Review review) {
        log.info("Updating review: {}", review);
        ReviewDomain domain = reviewRepository.findById(Math.toIntExact(review.getId()))
                .orElseThrow(() -> new ReviewUpdateException("Review not found with ID: " + review.getId()));
        domain.setRating(review.getRating());
        domain.setComment(review.getComment());
        domain.setReviewDate(review.getReviewDate());
        reviewRepository.save(domain);
        return true;
    }

    @Override
    public boolean deleteReview(Long id) {
        log.info("Deleting review ID: {}", id);
        if (!reviewRepository.existsById(Math.toIntExact(id))) {
            throw new ReviewDeleteException("Review not found with ID: " + id);
        }
        reviewRepository.deleteById(Math.toIntExact(id));
        return true;
    }

    // === Mapping Methods ===
    private ReviewDomain toDomain(Review review) {
        return ReviewDomain.builder()
                .id(review.getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .reviewDate(review.getReviewDate())
                //.restaurant(review.getRestaurant()) // must map to domain
                //.customer(review.getCustomer())     // must map to domain
                .build();
    }

    private Review toModel(ReviewDomain domain) {
        return Review.builder()
                .id(domain.getId())
                .rating(domain.getRating())
                .comment(domain.getComment())
                .reviewDate(domain.getReviewDate())
//                .restaurant(domain.getRestaurant()) // map back if needed
//                .customer(domain.getCustomer())     // map back if needed
                .build();
    }
}
