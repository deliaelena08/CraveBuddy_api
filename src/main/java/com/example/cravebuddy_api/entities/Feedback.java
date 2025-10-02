package com.example.cravebuddy_api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;
}
