package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.exception.PaymentAlreadyExistsException;
import com.shreya.hibernate.exception.PaymentNotFoundException;
import com.shreya.hibernate.model.Payment;
import com.shreya.hibernate.repository.PaymentRepository;
import com.shreya.hibernate.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean addPayment(Payment payment) throws SQLException {
        log.info("Add Payment: {}", payment);
        boolean exists = paymentRepository.findById(payment.getId().intValue()) != null;
        if (exists) {
            throw new PaymentAlreadyExistsException("Payment already exists with ID: " + payment.getId());
        }
        return paymentRepository.addPayment(payment);
    }

    @Override
    public List<Payment> getAllPayments() throws SQLException {
        log.info("Get All Payments");
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(int id) throws SQLException {
        log.info("Get Payment By ID: {}", id);
        Payment payment = paymentRepository.findById(id);
        if (payment == null) {
            throw new PaymentNotFoundException("Payment not found with ID: " + id);
        }
        return payment;
    }

    @Override
    public boolean updatePayment(Payment payment) throws SQLException {
        log.info("Update Payment: {}", payment);
        boolean updated = paymentRepository.update(payment);
        if (!updated) {
            throw new PaymentNotFoundException("Cannot update. Payment not found with ID: " + payment.getId());
        }
        return true;
    }

    @Override
    public boolean deletePayment(int id) throws SQLException {
        log.info("Delete Payment ID: {}", id);
        boolean deleted = paymentRepository.delete(id);
        if (!deleted) {
            throw new PaymentNotFoundException("Cannot delete. Payment not found with ID: " + id);
        }
        return true;
    }
}
