package com.example.cravebuddy_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BakeryDto {
    private Long id;

    @NotBlank(message = "Name of the bakery is mandatory")
    @Size(max = 50, message = "The name must be maximum 50 chars")
    private String name;

    @NotBlank(message = "The city is mandatory")
    @Size(max = 200, message = "The name of the city must be maximum 200 chars")
    private String city;

    @NotBlank(message = "The address is mandatory")
    @Size(max = 100, message = "The name of the address must be maximum 100 chars")
    private String address;

    private Float longitude;
    private Float latitude;
    private Long ownerId;
}