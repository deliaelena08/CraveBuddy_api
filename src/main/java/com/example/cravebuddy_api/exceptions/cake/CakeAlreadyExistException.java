package com.example.cravebuddy_api.exceptions.cake;

public class CakeAlreadyExistException extends RuntimeException {
    public CakeAlreadyExistException() {
        super("Cake already exists");
    }
}
