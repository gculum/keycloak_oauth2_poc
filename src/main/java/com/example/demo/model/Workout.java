package com.example.demo.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private String user;

    @Column(name="start_time")
    private LocalDateTime start;

    @Column(name="end_time")
    private LocalDateTime end;

    private int difficulty;
// Omitted getter and setters
}