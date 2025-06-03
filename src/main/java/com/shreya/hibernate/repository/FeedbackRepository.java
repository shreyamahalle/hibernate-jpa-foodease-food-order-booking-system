package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.FeedbackDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackDomain, Integer> {

}
