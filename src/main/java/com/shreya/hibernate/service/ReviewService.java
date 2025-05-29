package com.shreya.hibernate.service;

import com.shreya.hibernate.model.Review;

import java.sql.SQLException;
import java.util.List;

public interface ReviewService {

    boolean addReview(Review review) throws SQLException;

    List<Review> getAllReviews() throws SQLException;

    Review getReviewById(Long id);

    boolean updateReview(Review review);

    boolean deleteReview(Long id);
}
