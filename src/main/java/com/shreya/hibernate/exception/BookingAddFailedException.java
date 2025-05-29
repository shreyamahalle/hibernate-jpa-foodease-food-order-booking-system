package com.shreya.hibernate.exception;

public class BookingAddFailedException extends RuntimeException {
    public BookingAddFailedException(String message) {
        super(message);
    }
}
