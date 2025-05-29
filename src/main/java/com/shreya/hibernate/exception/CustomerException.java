package com.shreya.hibernate.exception;

public class CustomerException extends RuntimeException {

    public CustomerException(String NotAvailable) {
        super(NotAvailable);
    }
}
