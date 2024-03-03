package com.example.wound.entity;

import com.example.wound.rest.enums.WoundLocation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "wound")
@Getter
@Setter
public class WoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private WoundLocation location;

    private String name;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @OneToMany(mappedBy = "wound", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WoundPhaseEntity> woundPhases = new ArrayList<>();

}
