package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    void addRestaurant(Restaurant restaurant);
    List<Restaurant> retrieveRestaurants();
    Restaurant retrieveRestaurant(int id);
    boolean deleteRestaurant(int id);
    boolean updateRestaurant(int restaurant);
}
