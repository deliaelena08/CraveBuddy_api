package com.example.cravebuddy_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CakeDto {
    private Long id;
    private Integer sweetness;

    @NotBlank(message = "Name of the cake is mandatory")
    @Size(max = 50, message = "The name must be maximum 50 chars")
    private String nameRo;
    @NotBlank(message = "The list of ingredients is mandatory")
    @Size(max = 500, message = "The name must be maximum 500 chars")
    private String ingredientsRo;
    @NotBlank(message = "The texture is mandatory")
    @Size(max = 500, message = "The texture must be maximum 50 chars")
    private String textureRo;
    @NotBlank(message = "The flavour is mandatory")
    @Size(max = 500, message = "The texture must be maximum 50 chars")
    private String flavourRo;
    private String provenienceRo;

    @NotBlank(message = "Name of the cake is mandatory")
    @Size(max = 50, message = "The name must be maximum 50 chars")
    private String nameEn;
    @NotBlank(message = "The list of ingredients is mandatory")
    @Size(max = 500, message = "The name must be maximum 500 chars")
    private String ingredientsEn;
    @NotBlank(message = "The texture is mandatory")
    @Size(max = 500, message = "The texture must be maximum 50 chars")
    private String textureEn;
    @NotBlank(message = "The flavour is mandatory")
    @Size(max = 500, message = "The texture must be maximum 50 chars")
    private String flavourEn;
    private String provenienceEn;
}
