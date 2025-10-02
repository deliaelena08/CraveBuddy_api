package com.example.cravebuddy_api.dtos;

import lombok.Data;

@Data
public class MenuItemDto {
    private Long id;
    private CakeDto cake;
    private BakeryDto bakery;
    private Integer price;
    private Integer quantity;
}
