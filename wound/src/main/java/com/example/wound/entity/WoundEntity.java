package com.example.wound.entity;

import com.example.wound.rest.enums.WoundLocation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    private LocalDateTime date = LocalDateTime.now();

    @OneToMany(mappedBy = "wound", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WoundPhaseEntity> woundPhases = new ArrayList<>();

}
