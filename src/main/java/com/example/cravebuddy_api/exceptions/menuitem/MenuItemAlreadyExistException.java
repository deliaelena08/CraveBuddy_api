package com.example.cravebuddy_api.exceptions.menuitem;

public class MenuItemAlreadyExistException extends RuntimeException {
    public MenuItemAlreadyExistException() {
        super("Menu item already exists");
    }
}
