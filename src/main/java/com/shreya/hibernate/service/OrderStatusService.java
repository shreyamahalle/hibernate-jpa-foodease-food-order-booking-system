package com.shreya.hibernate.service;

import com.shreya.hibernate.model.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface OrderStatusService {

    boolean addOrderStatus(OrderStatus orderStatus) throws SQLException;

    List<OrderStatus> getAllOrderStatus() throws SQLException;

    OrderStatus getOrderStatusById(Long id) throws SQLException;

    boolean updateOrderStatus(OrderStatus orderStatus);

    boolean deleteOrderStatus(Long id);
}
