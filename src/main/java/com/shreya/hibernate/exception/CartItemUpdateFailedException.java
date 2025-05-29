package com.shreya.hibernate.exception;

public class CartItemUpdateFailedException extends RuntimeException {
    public CartItemUpdateFailedException(String message) {
        super(message);
    }
}
