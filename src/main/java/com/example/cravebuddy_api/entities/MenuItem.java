package com.example.cravebuddy_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cake_id")
    private Cake cake;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;
}
