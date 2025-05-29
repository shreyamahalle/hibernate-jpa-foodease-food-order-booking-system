package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

}
