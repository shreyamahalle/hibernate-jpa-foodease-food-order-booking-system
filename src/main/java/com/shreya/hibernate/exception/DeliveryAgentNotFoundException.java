package com.shreya.hibernate.exception;

public class DeliveryAgentNotFoundException extends RuntimeException {
    public DeliveryAgentNotFoundException(String message) {
        super(message);
    }
}