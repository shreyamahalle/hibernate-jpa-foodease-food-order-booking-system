package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.OrderDomain;
import com.shreya.hibernate.exception.OrderNotFoundException;
import com.shreya.hibernate.model.Order;
import com.shreya.hibernate.repository.OrderRepository;
import com.shreya.hibernate.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean addOrder(Order order) {
        log.info("Saving order: {}", order);
        orderRepository.save(toDomain(order));
        return true;
    }

    @Override
    public List<Order> retrieveAllOrders() {
        log.info("Fetching all orders");
        return orderRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Order retrieveOrderByIdAndType(int id, String type) {
        log.info("Fetching order by ID: {} and Type: {}", id, type);
        return orderRepository.findByIdAndType((long) id, type)
                .map(this::toModel)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id + " and Type: " + type));
    }

    @Override
    public String updateOrder(Order order) {
        Optional<OrderDomain> existing = orderRepository.findById(order.getId());
        if (existing.isEmpty()) {
            throw new OrderNotFoundException("Order not found with ID: " + order.getId());
        }
        OrderDomain updated = toDomain(order);
        orderRepository.save(updated);
        return updated.getType();
    }

    @Override
    public boolean deleteOrder(int id) {
        Optional<OrderDomain> existing = orderRepository.findById((int) id);
        if (existing.isEmpty()) {
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }
        orderRepository.deleteById((int) id);
        return true;
    }

    // === Mapping Methods ===
    private Order toModel(OrderDomain domain) {
        return Order.builder()
                .id(Math.toIntExact(domain.getId()))
                .type(domain.getType())
                .note(domain.getNote())
                .paymentMethod(domain.getPaymentMethod())
                .customerEmail(domain.getCustomerEmail())
                .build();
    }

    private OrderDomain toDomain(Order model) {
        return OrderDomain.builder()
                .id((long) model.getId())
                .type(model.getType())
                .note(model.getNote())
                .paymentMethod(model.getPaymentMethod())
                .customerEmail(model.getCustomerEmail())
                .build();
    }
}
