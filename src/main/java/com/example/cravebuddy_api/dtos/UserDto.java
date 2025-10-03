package com.example.cravebuddy_api.dtos;

import com.example.cravebuddy_api.utils.Language;
import com.example.cravebuddy_api.utils.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private Language language;
    private String location;
    private String icon;
}
