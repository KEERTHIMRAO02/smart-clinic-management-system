package com.project.back_end.models;

import jakarta.persistence.*;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String name;
    private String email;
    private String specialization;
    private String phone;

    @Column(length = 255)
    private String availableTimes;

    // Getters and Setters
}
