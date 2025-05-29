package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
