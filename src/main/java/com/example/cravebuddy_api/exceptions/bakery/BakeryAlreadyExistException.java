package com.example.cravebuddy_api.exceptions.bakery;

public class BakeryAlreadyExistException extends RuntimeException {
    public BakeryAlreadyExistException() {
        super("Bakery already exists");
    }
}
