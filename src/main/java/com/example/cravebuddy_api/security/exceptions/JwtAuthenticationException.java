package com.example.cravebuddy_api.security.exceptions;

public class JwtAuthenticationException extends RuntimeException{
    public JwtAuthenticationException(final Exception ex){
        super(ex);
    }
}
