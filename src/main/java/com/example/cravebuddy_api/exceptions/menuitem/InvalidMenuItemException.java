package com.example.cravebuddy_api.exceptions.menuitem;

public class InvalidMenuItemException extends RuntimeException {
    public InvalidMenuItemException() {
        super("Invalid menu item");
    }
}
