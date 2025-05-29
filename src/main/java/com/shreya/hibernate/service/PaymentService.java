package com.shreya.hibernate.service;

import com.shreya.hibernate.model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {

    boolean addPayment(Payment payment) throws SQLException;

    List<Payment> getAllPayments() throws SQLException;

    Payment getPaymentById(int id) throws SQLException;

    boolean updatePayment(Payment payment) throws SQLException;

    boolean deletePayment(int id) throws SQLException;
}
