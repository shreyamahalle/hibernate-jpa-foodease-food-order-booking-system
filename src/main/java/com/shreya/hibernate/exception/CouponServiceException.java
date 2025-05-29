package com.shreya.hibernate.exception;

public class CouponServiceException extends RuntimeException {
    public CouponServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}