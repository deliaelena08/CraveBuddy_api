package com.example.cravebuddy_api.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BakeryDto {
    private Long id;
    private String name;
    private String city;
    private Float longitude;
    private Float latitude;
    private String address;
}
