package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.DeliveryAgentDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgentDomain, Integer> {


}