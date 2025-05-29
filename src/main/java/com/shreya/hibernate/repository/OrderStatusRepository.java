package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {

}
