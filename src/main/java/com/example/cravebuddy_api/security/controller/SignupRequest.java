package com.example.cravebuddy_api.security.controller;

import com.example.cravebuddy_api.utils.Language;
import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private Language language;
    private String location;
    private String icon;
}
