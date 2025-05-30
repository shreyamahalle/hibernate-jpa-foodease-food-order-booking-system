package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.OrderStatusDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatusDomain,Integer> {

}
