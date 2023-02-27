package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
}