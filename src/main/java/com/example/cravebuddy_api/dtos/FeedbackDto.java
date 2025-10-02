package com.example.cravebuddy_api.dtos;

import lombok.Data;

@Data
public class FeedbackDto {
    private Long id;
    private String message;
    private UserDto user;
    private BakeryDto bakery;
}
