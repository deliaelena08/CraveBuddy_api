package com.example.cravebuddy_api.exceptions.menuitem;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("Menu item not found");
    }
}
