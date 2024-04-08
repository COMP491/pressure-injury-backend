package com.example.wound.service;

import com.example.wound.dto.PatientDetailsDTO;
import com.example.wound.dto.PatientNamesDTO;
import com.example.wound.dto.PatientWoundsDTO;
import com.example.wound.entity.PatientEntity;
import com.example.wound.entity.WoundEntity;
import com.example.wound.repository.PatientRepository;
import com.example.wound.rest.requests.AddPatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientNamesDTO> getPatientNames() {
        List<PatientEntity> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        List<PatientNamesDTO> patientNames = new ArrayList<>();

        for (PatientEntity patient : patients) {
            PatientNamesDTO dto = new PatientNamesDTO();
            dto.setId(patient.getId());
            dto.setName(patient.getName());
            patientNames.add(dto);
        }
        return patientNames;
    }

    public PatientDetailsDTO getPatientDetails(String barcode) {
        PatientEntity patient = patientRepository.findByBarcode(barcode);
        if (patient == null) {
            throw new IllegalArgumentException("No patient found with barcode: " + barcode);
        }
        PatientDetailsDTO dto = new PatientDetailsDTO();
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setBarcode(patient.getBarcode());
        dto.setGender(patient.getGender());

        return dto;
    }

    public List<PatientWoundsDTO> getPatientWoundsById(Long id) {
        PatientEntity patient;
        try {
            patient = patientRepository.findAllById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }

        List<PatientWoundsDTO> patientWounds = new ArrayList<>();

        for (WoundEntity wound : patient.getWounds()) {
            PatientWoundsDTO dto = new PatientWoundsDTO();
            dto.setId(wound.getId());
            dto.setLocation(wound.getLocation());
            patientWounds.add(dto);
        }
        return patientWounds;
    }

    public List<PatientWoundsDTO> getPatientWoundsByBarcode(String barcode) {
        PatientEntity patient;
        try {
            patient = patientRepository.findByBarcode(barcode);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.toString());
        }

        List<PatientWoundsDTO> patientWounds = new ArrayList<>();

        for (WoundEntity wound : patient.getWounds()) {
            PatientWoundsDTO dto = new PatientWoundsDTO();
            dto.setId(wound.getId());
            dto.setLocation(wound.getLocation());
            patientWounds.add(dto);
        }
        return patientWounds;
    }

    public String addPatient(AddPatientRequest request) {
        PatientEntity patient = new PatientEntity();
        patient.setAge(request.getAge());
        patient.setBarcode(request.getBarcode());
        patient.setName(request.getName());
        patient.setGender(request.getGender());
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            return e.toString();
        }
        return "Patient with barcode: " + patient.getBarcode() + " was successfuly added";
    }
}
