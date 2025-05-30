package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.FeedbackDomain;
import com.shreya.hibernate.exception.FeedbackCreationException;
import com.shreya.hibernate.exception.FeedbackNotFoundException;
import com.shreya.hibernate.model.Feedback;
import com.shreya.hibernate.repository.FeedbackRepository;
import com.shreya.hibernate.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public boolean addFeedback(Feedback feedback) {
        log.info("Saving feedback: {}", feedback);
        try {
            FeedbackDomain saved = feedbackRepository.save(populateDomain(feedback));
            return saved.getId() != null;
        } catch (Exception e) {
            throw new FeedbackCreationException("Unable to add feedback.");
        }
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        log.info("Fetching all feedbacks");
        return feedbackRepository.findAll()
                .stream()
                .map(this::populateModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        log.info("Fetching feedback by id: {}", id);
        return feedbackRepository.findById(Math.toIntExact(id))
                .map(this::populateModel)
                .or(() -> {
                    throw new FeedbackNotFoundException("Feedback not found with ID: " + id);
                });
    }

    @Override
    public boolean deleteFeedback(Long id) {
        log.info("Deleting feedback by id: {}", id);
        Optional<FeedbackDomain> optional = feedbackRepository.findById(Math.toIntExact(id));
        if (optional.isPresent()) {
            feedbackRepository.deleteById(Math.toIntExact(id));
            return true;
        } else {
            throw new FeedbackNotFoundException("Cannot delete. Feedback not found with ID: " + id);
        }
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        log.info("Updating feedback: {}", feedback);
        if (!feedbackRepository.existsById(Math.toIntExact(feedback.getId()))) {
            throw new FeedbackNotFoundException("Cannot update. Feedback not found with ID: " + feedback.getId());
        }
        feedbackRepository.save(populateDomain(feedback));
        return true;
    }

    // === Mapping Methods ===
    private Feedback populateModel(FeedbackDomain domain) {
        return Feedback.builder()
                .id(domain.getId())
                .feedbackDate(domain.getFeedbackDate())
                .comment(domain.getComment())
                .rating(domain.getRating())
//                .order(domain.getOrder())
//                .customer(domain.getCustomer())
                .build();
    }

    private FeedbackDomain populateDomain(Feedback model) {
        return FeedbackDomain.builder()
                .id(model.getId())
                .comment(model.getComment())
                .feedbackDate(model.getFeedbackDate())
                .rating(model.getRating())
//                .order(model.getOrder())
//                .customer(model.getCustomer())
                .build();
    }
}
