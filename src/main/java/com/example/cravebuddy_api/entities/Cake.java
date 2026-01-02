package com.example.cravebuddy_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cakes")
public class Cake {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_ro")
    private String nameRo;

    @Column(name="ingredients_ro")
    private String ingredientsRo;

    @Column(name = "texture_ro")
    private String textureRo;

    @Column(name = "flavour_ro")
    private String flavourRo;

    @Column(name = "provenience_ro")
    private String provenienceRo;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name="ingredients_en")
    private String ingredientsEn;

    @Column(name = "texture_en")
    private String textureEn;

    @Column(name = "flavour_en")
    private String flavourEn;

    @Column(name = "provenience_en")
    private String provenienceEn;

    @Column(name = "sweetness")
    private Integer sweetness;
}
