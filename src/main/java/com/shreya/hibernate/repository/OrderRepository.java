package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.Order;
import java.util.List;

public interface OrderRepository {
    boolean addOrder(Order order);
    List<Order> retrieveOrders();
    Order retrieveOrder(int id, String type);
    boolean deleteOrder(int id);
    boolean updateOrder(int id, String newType);
}
