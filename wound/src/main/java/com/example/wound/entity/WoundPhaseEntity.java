package com.example.wound.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "wound_phase")
@Getter
@Setter
public class WoundPhaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double grade;

    private Double diameter;

    private String date;

    private String imagePath;



    @ManyToOne
    @JoinColumn(name = "wound_id")
    private WoundEntity wound;
}
