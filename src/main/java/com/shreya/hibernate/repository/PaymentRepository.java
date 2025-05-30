package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.PaymentDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentDomain,Integer> {

}
