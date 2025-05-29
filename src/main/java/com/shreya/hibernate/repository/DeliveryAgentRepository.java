package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent,Integer> {


}