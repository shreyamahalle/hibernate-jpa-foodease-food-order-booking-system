package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.DeliveryAgent;

import java.util.List;

public interface DeliveryAgentRepository {

    boolean addDeliveryAgent(DeliveryAgent deliveryAgent);

    List<DeliveryAgent> retrieveDeliveryAgents();

    DeliveryAgent findById(int id);

    boolean deleteDeliveryAgent(int id);

    boolean updateDeliveryAgent(DeliveryAgent deliveryAgent);
}