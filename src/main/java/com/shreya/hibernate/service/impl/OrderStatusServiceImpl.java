package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.OrderStatusDomain;
import com.shreya.hibernate.exception.OrderStatusNotFoundException;
import com.shreya.hibernate.model.OrderStatus;
import com.shreya.hibernate.repository.OrderStatusRepository;
import com.shreya.hibernate.service.OrderStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public boolean addOrderStatus(OrderStatus orderStatus) {
        log.info("Saving Order Status: {}", orderStatus);
        orderStatusRepository.save(toDomain(orderStatus));
        return true;
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        log.info("Fetching all order statuses");
        return orderStatusRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public OrderStatus getOrderStatusById(Long id) {
        log.info("Fetching order status by ID: {}", id);
        OrderStatusDomain domain = orderStatusRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new OrderStatusNotFoundException("OrderStatus with ID " + id + " not found"));
        return toModel(domain);
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus) {
        if (!orderStatusRepository.existsById((int) orderStatus.getId())) {
            throw new OrderStatusNotFoundException("OrderStatus with ID " + orderStatus.getId() + " not found");
        }
        orderStatusRepository.save(toDomain(orderStatus));
        return true;
    }

    @Override
    public boolean deleteOrderStatus(Long id) {
        if (!orderStatusRepository.existsById(Math.toIntExact(id))) {
            throw new OrderStatusNotFoundException("OrderStatus with ID " + id + " not found");
        }
        orderStatusRepository.deleteById(Math.toIntExact(id));
        return true;
    }

    // === Mapping Methods ===
    private OrderStatus toModel(OrderStatusDomain domain) {
        return OrderStatus.builder()
                .id(domain.getId())
                .status(domain.getStatus())
                .description(domain.getDescription())
                .build();
    }

    private OrderStatusDomain toDomain(OrderStatus model) {
        return OrderStatusDomain.builder()
                .id(model.getId())
                .status(model.getStatus())
                .description(model.getDescription())
                .build();
    }
}
