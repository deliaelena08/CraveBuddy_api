package com.example.cravebuddy_api.exceptions.favourite;

public class FavouriteAlreadyExistException extends RuntimeException {
    public FavouriteAlreadyExistException() {
        super("Favourite already exists");
    }
}
