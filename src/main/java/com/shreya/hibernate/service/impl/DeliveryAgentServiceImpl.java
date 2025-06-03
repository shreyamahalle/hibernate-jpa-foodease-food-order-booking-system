package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.DeliveryAgentDomain;
import com.shreya.hibernate.exception.DeliveryAgentDeleteException;
import com.shreya.hibernate.exception.DeliveryAgentNotFoundException;
import com.shreya.hibernate.exception.DeliveryAgentUpdateException;
import com.shreya.hibernate.model.DeliveryAgent;
import com.shreya.hibernate.repository.DeliveryAgentRepository;
import com.shreya.hibernate.service.DeliveryAgentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    private final Logger log = LoggerFactory.getLogger(DeliveryAgentServiceImpl.class);
    private final DeliveryAgentRepository deliveryAgentRepository;

    @Override
    public DeliveryAgent addDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        log.info("Saving DeliveryAgent: {}", deliveryAgent);
        DeliveryAgentDomain domain = this.populateDomain(deliveryAgent);
        try {
            deliveryAgentRepository.save(domain);
            log.info("DeliveryAgent added successfully{}", deliveryAgent);
        } catch (Exception e) {
            throw new DeliveryAgentNotFoundException("Failed to add deliveryAgent");
        }
        return this.populateModel(deliveryAgentRepository.save(domain));
    }

    @Override
    public boolean updateDeliveryAgent(DeliveryAgent deliveryAgent) throws SQLException {
        log.info("Updating DeliveryAgent: {}", deliveryAgent);
        if (!updateDeliveryAgent(deliveryAgent)) {
            throw new DeliveryAgentUpdateException("Failed to update delivery agent with ID: " + deliveryAgent.getId());
        }
        return false;
    }

    @Override
    public boolean deleteDeliveryAgent(int id) throws SQLException {
        log.info("Deleting DeliveryAgent with ID: {}", id);
        Optional<DeliveryAgentDomain> domain = deliveryAgentRepository.findById(id);
        if (!domain.isEmpty()) {
            log.info("Successfully deleted DeliveryAgent with ID: {}", id);
            return true;
        } else {
            log.error("Failed to delete DeliveryAgent with ID: {}", id);
            throw new DeliveryAgentDeleteException("Failed to delete delivery agent with ID: " + id);
        }
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(int id) throws SQLException {
        log.info("Fetching DeliveryAgent by ID: {}", id);
        Optional<DeliveryAgentDomain> agent = deliveryAgentRepository.findById(id);
        if (agent == null) {
            throw new DeliveryAgentNotFoundException("Delivery agent not found with ID: " + id);
        }
        return this.populateModel(agent.get());
    }

    @Override
    public List<DeliveryAgent> retrieveAllDeliveryAgents() throws SQLException {
        log.info("Fetching all DeliveryAgents");
        List<DeliveryAgent> deliveryAgents = deliveryAgentRepository.findAll().stream().map(this::populateModel).toList();

        if (!deliveryAgents.isEmpty()) {
            log.info("Retrieved {} deliveryAgent", deliveryAgents.size());
            return deliveryAgents;
        } else {
            log.error("No customers found");
            throw new DeliveryAgentNotFoundException("No customers available");
        }
    }

    private DeliveryAgent populateModel(DeliveryAgentDomain domain) {
        return DeliveryAgent.builder()
                .id(domain.getId())
                .name(domain.getName())
                .city(domain.getCity())
                .mobileNo(domain.getMobileNo())
                .build();

    }

    private DeliveryAgentDomain populateDomain(DeliveryAgent model) {
        return DeliveryAgentDomain.builder()
                .id(model.getId())
                .name(model.getName())
                .city(model.getCity())
                .mobileNo(model.getMobileNo())
                .build();

    }
}