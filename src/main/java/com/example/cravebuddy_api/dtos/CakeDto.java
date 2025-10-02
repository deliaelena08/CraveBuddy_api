package com.example.cravebuddy_api.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CakeDto {
    private Long id;
    private String name;
    private String ingredients;
    private String texture;
    private String flavour;
    private Integer sweetness;
    private String provenience;
}
