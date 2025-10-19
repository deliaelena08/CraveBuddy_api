package com.example.cravebuddy_api.exceptions.user;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }
}
