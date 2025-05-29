package com.shreya.hibernate.exception;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("MenuItem not found");
    }

    public MenuItemNotFoundException(String message) {
        super(message);
    }

}
