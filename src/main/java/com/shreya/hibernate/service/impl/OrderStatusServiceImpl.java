package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.exception.DatabaseException;
import com.shreya.hibernate.exception.OrderStatusNotFoundException;
import com.shreya.hibernate.model.OrderStatus;
import com.shreya.hibernate.repository.OrderStatusRepository;
import com.shreya.hibernate.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final Logger log = LoggerFactory.getLogger(OrderStatusServiceImpl.class);

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public boolean addOrderStatus(OrderStatus orderStatus) {
        log.info("Adding Order Status: {}", orderStatus);
        return orderStatusRepository.addOrderStatus(orderStatus);
    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        log.info("Fetching all Order Statuses");
        return orderStatusRepository.retrieveOrderStatuses();
    }

    @Override
    public OrderStatus getOrderStatusById(Long id) {
        log.info("Fetching Order Status by ID: {}", id);
        OrderStatus status = orderStatusRepository.retrieveOrderStatus(id);
        if (status == null) {
            throw new OrderStatusNotFoundException("OrderStatus with ID " + id + " not found");
        }
        return status;
    }

    @Override
    public boolean updateOrderStatus(OrderStatus orderStatus) {
        log.info("Updating Order Status: {}", orderStatus);
        boolean updated = orderStatusRepository.updateOrderStatus(orderStatus);
        if (!updated) {
            throw new OrderStatusNotFoundException("OrderStatus with ID " + orderStatus.getId() + " not found");
        }
        return true;
    }

    @Override
    public boolean deleteOrderStatus(Long id) {
        log.info("Deleting Order Status with ID: {}", id);
        boolean deleted = orderStatusRepository.deleteOrderStatus(id);
        if (!deleted) {
            throw new OrderStatusNotFoundException("OrderStatus with ID " + id + " not found");
        }
        return true;
    }
}
