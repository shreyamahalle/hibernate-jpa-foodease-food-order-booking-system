package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.CartItemDomain;
import com.shreya.hibernate.exception.CartItemAddFailedException;
import com.shreya.hibernate.exception.CartItemNotFoundException;
import com.shreya.hibernate.exception.CartItemUpdateFailedException;
import com.shreya.hibernate.model.CartItem;
import com.shreya.hibernate.repository.CartItemRepository;
import com.shreya.hibernate.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private static final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final CartItemRepository cartItemRepository;

    @Override
    public Object addCartItem(CartItem cartItem) throws SQLException {
        log.info("Saving CartItem: {}", cartItem);
        CartItemDomain domain = populateDomain(cartItem);
        CartItemDomain saved = cartItemRepository.save(domain);
        if (saved == null) {
            log.error("Failed to add CartItem: {}", cartItem);
            throw new CartItemAddFailedException("Failed to add CartItem");
        }
        return populateModel(saved);
    }

    @Override
    public boolean deleteCartItem(int id) {
        log.info("Deleting CartItem with ID: {}", id);
        Optional<CartItemDomain> optionalDomain = cartItemRepository.findById(id);
        if (optionalDomain.isEmpty()) {
            log.error("CartItem not found for ID: {}", id);
            throw new CartItemNotFoundException("CartItem not found for ID: " + id);
        }
        cartItemRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) throws SQLException {
        log.info("Updating CartItem: {}", cartItem);
        Optional<CartItemDomain> optionalDomain = cartItemRepository.findById(Math.toIntExact(cartItem.getId()));

        if (optionalDomain.isEmpty()) {
            log.error("CartItem not found for ID: {}", cartItem.getId());
            throw new CartItemNotFoundException("CartItem not found for ID: " + cartItem.getId());
        }

        CartItemDomain existing = optionalDomain.get();
        existing.setMenuItemId(cartItem.getMenu_item_id());
        existing.setCustomerId(cartItem.getCustomer_id());
        existing.setQuantity(cartItem.getQuantity());

        CartItemDomain updated = cartItemRepository.save(existing);
        if (updated == null) {
            throw new CartItemUpdateFailedException("Failed to update CartItem");
        }

        return true;
    }

    @Override
    public List<CartItem> retrieveCartItem() {
        log.info("Retrieving all cart items");
        return cartItemRepository.findAll().stream().map(this::populateModel).toList();
    }

    @Override
    public Optional<CartItem> getCartItem(int id) {
        log.info("Fetching cart item with ID: {}", id);
        Optional<CartItemDomain> optionalDomain = cartItemRepository.findById(id);
        if (optionalDomain.isEmpty()) {
            throw new CartItemNotFoundException("CartItem not found for ID: " + id);
        }
        return Optional.of(populateModel(optionalDomain.get()));
    }

    @Override
    public boolean updatePartialCartItem(CartItem cartItem) {
        log.info("Partially updating CartItem: {}", cartItem);
        Optional<CartItemDomain> optionalDomain = cartItemRepository.findById(Math.toIntExact(cartItem.getId()));

        if (optionalDomain.isEmpty()) {
            throw new CartItemNotFoundException("CartItem not found for ID: " + cartItem.getId());
        }

        CartItemDomain existing = optionalDomain.get();

        if (cartItem.getQuantity() != null) existing.setQuantity(cartItem.getQuantity());
        if (cartItem.getCustomer_id() != null) existing.setCustomerId(cartItem.getCustomer_id());
        if (cartItem.getMenu_item_id() != null) existing.setMenuItemId(cartItem.getMenu_item_id());

        cartItemRepository.save(existing);
        return true;
    }

    private CartItem populateModel(CartItemDomain domain) {
        return CartItem.builder()
                .id(domain.getId())
                .menu_item_id(domain.getMenuItemId())
                .customer_id(domain.getCustomerId())
                .quantity(domain.getQuantity())
                .build();
    }

    private CartItemDomain populateDomain(CartItem model) {
        return CartItemDomain.builder()
                .id(model.getId())
                .menuItemId(model.getMenu_item_id())
                .customerId(model.getCustomer_id())
                .quantity(model.getQuantity())
                .build();
    }
}
