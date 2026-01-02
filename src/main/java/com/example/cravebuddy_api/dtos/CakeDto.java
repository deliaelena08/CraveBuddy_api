package com.example.cravebuddy_api.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CakeDto {
    private Long id;
    private Integer sweetness;

    private String nameRo;
    private String ingredientsRo;
    private String textureRo;
    private String flavourRo;
    private String provenienceRo;

    private String nameEn;
    private String ingredientsEn;
    private String textureEn;
    private String flavourEn;
    private String provenienceEn;
}
