package com.example.cravebuddy_api.exceptions.favourite;

public class FavouriteNotFoundException extends RuntimeException {
    public FavouriteNotFoundException() {
        super("Favourite not found");
    }
}
