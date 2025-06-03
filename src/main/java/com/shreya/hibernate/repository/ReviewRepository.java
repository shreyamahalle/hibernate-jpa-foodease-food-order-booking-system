package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.ReviewDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewDomain, Integer> {


}