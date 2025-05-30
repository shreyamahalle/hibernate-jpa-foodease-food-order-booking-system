package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.RestaurantDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantDomain,Integer> {

}
