package com.example.cravebuddy_api.security.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
