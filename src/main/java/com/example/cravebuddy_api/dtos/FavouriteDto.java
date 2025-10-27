package com.example.cravebuddy_api.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FavouriteDto {
    private Long id;
    private LocalDateTime createdAt;
    private UserDto user;
    private BakeryDto bakery;
}
