package com.shreya.hibernate.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Booking not found.");
    }

    public BookingNotFoundException(String message) {
        super(message);
    }
}
