package com.shreya.hibernate.controller;

import com.shreya.hibernate.exception.CartItemAddFailedException;
import com.shreya.hibernate.exception.CartItemNotFoundException;
import com.shreya.hibernate.exception.CartItemUpdateFailedException;
import com.shreya.hibernate.model.CartItem;
import com.shreya.hibernate.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartItemManagement")
public class CartItemController {

    private final Logger log = LoggerFactory.getLogger(CartItemController.class);

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cartItem")
    public ResponseEntity<String> addCartItem(@RequestBody CartItem cartItem) throws SQLException {
        log.info("API called: add cartItem {}", cartItem);
        Object added = cartItemService.addCartItem(cartItem);
        if (!(boolean) added) {
            throw new CartItemAddFailedException("Failed to add CartItem.");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("CartItem added successfully.");
        }
    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        log.info("API called: get all cartItems");
        List<CartItem> cartItems = cartItemService.retrieveCartItem();
        if (cartItems == null || cartItems.isEmpty()) {
            throw new CartItemNotFoundException("No cart items found.");
        }
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/cartItem/{id}")
    public ResponseEntity<Optional<CartItem>> getCartItemById(@PathVariable int id) {
        log.info("API called: get CartItem by Id {}", id);
        Optional<CartItem> cartItem = cartItemService.getCartItem(id);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/cartItem/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
        log.info("API called: delete CartItem by Id {}", id);
        boolean deleted = cartItemService.deleteCartItem(id);
        if (deleted) {
            return ResponseEntity.ok("CartItem deleted successfully.");
        } else {
            throw new CartItemNotFoundException("CartItem not found with id: " + id);
        }
    }

    @PatchMapping("/cartItem")
    public ResponseEntity<String> updatePartialCartItem(@RequestBody CartItem cartItem) {
        log.info("API called: update partial CartItem {}", cartItem);
        boolean updated = cartItemService.updatePartialCartItem(cartItem);
        if (updated) {
            return ResponseEntity.ok("CartItem updated partially.");
        } else {
            throw new CartItemUpdateFailedException("Failed to update CartItem partially.");
        }
    }

    @PutMapping("/cartItem")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItem cartItem) throws SQLException {
        log.info("API called: update CartItem {}", cartItem);
        boolean updated = cartItemService.updateCartItem(cartItem);
        if (updated) {
            return ResponseEntity.ok("CartItem updated successfully.");
        } else {
            throw new CartItemUpdateFailedException("Failed to update CartItem.");
        }
    }
}
