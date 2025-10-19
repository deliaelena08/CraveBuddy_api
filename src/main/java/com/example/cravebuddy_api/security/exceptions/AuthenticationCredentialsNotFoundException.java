package com.example.cravebuddy_api.security.exceptions;

public class AuthenticationCredentialsNotFoundException extends RuntimeException{
    public AuthenticationCredentialsNotFoundException(final String message){
        super(message);
    }
}
