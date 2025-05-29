package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository {

    boolean addCartItem(CartItem cartItem);

    List<CartItem> retrieveCartItems();

    Optional<CartItem> findById(int id);

    boolean deleteCartItem(int id);

    boolean updateCartItem(CartItem cartItem);

    boolean updatePartialCartItem(CartItem cartItem);
}