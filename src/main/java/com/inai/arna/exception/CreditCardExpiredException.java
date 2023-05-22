package com.inai.arna.exception;

public class CreditCardExpiredException extends RuntimeException {
    public CreditCardExpiredException(String message) {
        super(message);
    }
}
