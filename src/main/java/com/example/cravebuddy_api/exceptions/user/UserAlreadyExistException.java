package com.example.cravebuddy_api.exceptions.user;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(){
        super("User already exists!");
    }
}