package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.OrderStatus;
import java.util.List;

public interface OrderStatusRepository {
    boolean addOrderStatus(OrderStatus orderStatus);
    List<OrderStatus> retrieveOrderStatuses();
    OrderStatus retrieveOrderStatus(long id);
    boolean updateOrderStatus(OrderStatus orderStatus);
    boolean deleteOrderStatus(long id);
}
