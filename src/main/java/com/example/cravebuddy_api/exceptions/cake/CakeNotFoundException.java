package com.example.cravebuddy_api.exceptions.cake;

public class CakeNotFoundException extends RuntimeException{
    public CakeNotFoundException() {
        super("Cake not found");
    }
}
