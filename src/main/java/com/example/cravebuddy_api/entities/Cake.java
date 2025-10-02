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

    @Column(name = "name")
    private String name;

    @Column(name="ingredients")
    private String ingredients;

    @Column(name = "texture")
    private String texture;

    @Column(name = "flavour")
    private String flavour;

    @Column(name = "sweetness")
    private Integer sweetness;

    @Column(name = "provenience")
    private String provenience;
}
