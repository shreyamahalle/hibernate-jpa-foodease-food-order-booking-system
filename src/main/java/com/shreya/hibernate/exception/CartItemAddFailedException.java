package com.shreya.hibernate.exception;

public class CartItemAddFailedException extends RuntimeException {
    public CartItemAddFailedException(String message) {
        super(message);
    }
}
