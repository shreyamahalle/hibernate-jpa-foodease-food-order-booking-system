package com.shreya.hibernate.service;

import com.shreya.hibernate.model.CartItem;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CartItemService {

    boolean addCartItem(CartItem cartItem) throws SQLException;

    boolean deleteCartItem(int id);

    boolean updateCartItem(CartItem cartItem) throws SQLException;

    List<CartItem> retrieveCartItem();

    Optional<CartItem> getCartItem(int id);

    boolean updatePartialCartItem(CartItem cartItem);
}