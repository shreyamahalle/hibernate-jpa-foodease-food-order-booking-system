package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.OrderDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderDomain, Integer> {
    Optional<OrderDomain> findByIdAndType(Long id, String type);
}
