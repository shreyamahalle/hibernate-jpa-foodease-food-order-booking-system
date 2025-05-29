package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.Payment;

import java.util.List;

public interface PaymentRepository {
    boolean addPayment(Payment payment);
    List<Payment> findAll();
    Payment findById(int id);
    boolean update(Payment payment);
    boolean delete(int id);
}
