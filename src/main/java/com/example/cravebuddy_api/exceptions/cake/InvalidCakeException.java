package com.example.cravebuddy_api.exceptions.cake;

public class InvalidCakeException extends RuntimeException {
    public InvalidCakeException() {
        super("Invalid cake");
    }
}
