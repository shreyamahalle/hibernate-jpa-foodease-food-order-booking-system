package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.PaymentDomain;
import com.shreya.hibernate.exception.PaymentAlreadyExistsException;
import com.shreya.hibernate.exception.PaymentNotFoundException;
import com.shreya.hibernate.model.Payment;
import com.shreya.hibernate.repository.PaymentRepository;
import com.shreya.hibernate.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean addPayment(Payment payment) {
        log.info("Add Payment: {}", payment);
        if (paymentRepository.existsById(Math.toIntExact(payment.getId()))) {
            throw new PaymentAlreadyExistsException("Payment already exists with ID: " + payment.getId());
        }
        paymentRepository.save(toDomain(payment));
        return true;
    }

    @Override
    public List<Payment> getAllPayments() {
        log.info("Get All Payments");
        return paymentRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Payment getPaymentById(int id) {
        log.info("Get Payment By ID: {}", id);
        PaymentDomain domain = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
        return toModel(domain);
    }

    @Override
    public boolean updatePayment(Payment payment) {
        log.info("Update Payment: {}", payment);
        if (!paymentRepository.existsById(Math.toIntExact(payment.getId()))) {
            throw new PaymentNotFoundException("Cannot update. Payment not found with ID: " + payment.getId());
        }
        paymentRepository.save(toDomain(payment));
        return true;
    }

    @Override
    public boolean deletePayment(int id) {
        log.info("Delete Payment ID: {}", id);
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Cannot delete. Payment not found with ID: " + id);
        }
        paymentRepository.deleteById(id);
        return true;
    }

    // === Mapping Methods ===
    private Payment toModel(PaymentDomain domain) {
        return Payment.builder()
                .id(domain.getId())
                .amount(domain.getAmount())
                .paymentMethod(domain.getPaymentMethod())
                .paymentStatus(domain.getPaymentStatus())
                .transactionId(domain.getTransactionId())
                //.order(domain.getOrder().getId()) // assuming order ID is needed
                .build();
    }

    private PaymentDomain toDomain(Payment model) {
        return PaymentDomain.builder()
                .id(model.getId())
                .amount(model.getAmount())
                .paymentMethod(model.getPaymentMethod())
                .paymentStatus(model.getPaymentStatus())
                .transactionId(model.getTransactionId())
                //.order(OrderDomain.builder().id(model.getOrderId()).build()) // minimal object with only ID
                .build();
    }
}
