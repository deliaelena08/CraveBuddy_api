package com.example.cravebuddy_api.exceptions.user;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String errs){
        super(errs);
    }
}
