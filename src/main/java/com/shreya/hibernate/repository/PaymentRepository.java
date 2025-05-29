package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
