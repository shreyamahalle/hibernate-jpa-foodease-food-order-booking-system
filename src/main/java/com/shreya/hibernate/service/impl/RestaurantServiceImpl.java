package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.RestaurantDomain;
import com.shreya.hibernate.exception.RestaurantDeleteException;
import com.shreya.hibernate.exception.RestaurantNotFoundException;
import com.shreya.hibernate.exception.RestaurantUpdateException;
import com.shreya.hibernate.model.Restaurant;
import com.shreya.hibernate.repository.RestaurantRepository;
import com.shreya.hibernate.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void insertRestaurant(Restaurant restaurant) {
        log.info("Insert Restaurant: {}", restaurant);
        restaurantRepository.save(toDomain(restaurant));
    }

    @Override
    public List<Restaurant> retrieveRestaurants() {
        log.info("Retrieve all restaurants");
        return restaurantRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        log.info("Get restaurant by ID: {}", id);
        RestaurantDomain domain = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + id));
        return toModel(domain);
    }

    @Override
    public boolean deleteRestaurant(int id) {
        log.info("Delete restaurant ID: {}", id);
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantDeleteException("Failed to delete restaurant with ID: " + id);
        }
        restaurantRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateRestaurant(int id) {
        log.info("Update restaurant ID: {}", id);
        RestaurantDomain domain = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantUpdateException("Cannot update. Restaurant not found with ID: " + id));
        restaurantRepository.save(domain); // re-save after making changes
        return true;
    }

    // === Mapping Methods ===
    private Restaurant toModel(RestaurantDomain domain) {
        return Restaurant.builder()
                .id(domain.getId())
                .name(domain.getName())
                .City(domain.getCity())
                .Area(domain.getArea())
                .build();
    }

    private RestaurantDomain toDomain(Restaurant model) {
        return RestaurantDomain.builder()
                .id(model.getId())
                .name(model.getName())
                .city(model.getCity())
                .area(model.getArea())
                .build();
    }
}
