package com.shreya.hibernate.exception;

public class OrderStatusAlreadyExistsException extends RuntimeException {
    public OrderStatusAlreadyExistsException(String message) {
        super(message);
    }
}
