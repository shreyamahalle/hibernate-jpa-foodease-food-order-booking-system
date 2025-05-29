package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository {
    boolean addFeedback(Feedback feedback);
    List<Feedback> retrieveFeedbacks();
    Optional<Feedback> findById(Long id);
    boolean deleteFeedback(Long id);
    boolean updateFeedback(Feedback feedback);
}
