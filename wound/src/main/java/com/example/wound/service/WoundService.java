package com.example.wound.service;

import com.example.wound.entity.PatientEntity;
import com.example.wound.entity.WoundEntity;
import com.example.wound.entity.WoundPhaseEntity;
import com.example.wound.repository.PatientRepository;
import com.example.wound.repository.WoundPhaseRepository;
import com.example.wound.repository.WoundRepository;
import com.example.wound.rest.requests.AddWoundPhaseRequest;
import com.example.wound.rest.requests.AddWoundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class WoundService {

    @Autowired
    private WoundRepository woundRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WoundPhaseRepository woundPhaseRepository;


    public List<WoundPhaseEntity> getWoundPhases(Long id) {
        return woundRepository.findAllById(id).getWoundPhases();
    }

    public String addWound(AddWoundRequest request) {

        try {
            PatientEntity patient = patientRepository.findAllById(request.getPatientId());
            WoundEntity wound = new WoundEntity();
            wound.setPatient(patient);
            wound.setName(request.getName());
            wound.setLocation(request.getLocation());

            patient.getWounds().add(wound);
            patientRepository.save(patient);

        } catch (Exception e) {
            return e.toString();
        }

        return "Wound successfully added";
    }

    public String addWoundPhase(MultipartFile image, AddWoundPhaseRequest request) {

        try {
            WoundPhaseEntity woundPhase = new WoundPhaseEntity();
            WoundEntity wound = woundRepository.findAllById(request.getWoundId());
            woundPhase.setWound(wound);
            woundPhase.setDate(request.getDate());
            woundPhase.setSeverity(request.getSeverity());
            woundPhase.setTemperature(request.getTemperature());
            wound.getWoundPhases().add(woundPhase);

            woundPhaseRepository.save(woundPhase);

        } catch (Exception e) {
            return e.toString();
        }

        return "Wound phase successfully added";

    }
}
