package com.samimiyetsiz.samimiyetsizapi.web.rest.errors;

public class AccountResourceException extends RuntimeException {
    public AccountResourceException(String message) {
        super(message);
    }
}