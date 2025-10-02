package com.example.cravebuddy_api.exceptions.bakery;

public class BakeryNotFoundException extends RuntimeException {
    public BakeryNotFoundException() {
        super("Bakery not found");
    }
}
