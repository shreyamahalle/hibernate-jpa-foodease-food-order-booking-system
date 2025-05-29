package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {


}