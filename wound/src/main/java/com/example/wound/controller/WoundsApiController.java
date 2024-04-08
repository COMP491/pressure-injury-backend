package com.example.wound.controller;

import com.example.wound.dto.PatientDetailsDTO;
import com.example.wound.dto.PatientNamesDTO;
import com.example.wound.dto.PatientWoundsDTO;
import com.example.wound.entity.WoundPhaseEntity;
import com.example.wound.rest.requests.AddPatientRequest;
import com.example.wound.rest.requests.AddWoundPhaseRequest;
import com.example.wound.rest.requests.AddWoundRequest;
import com.example.wound.service.PatientService;
import com.example.wound.service.WoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WoundsApiController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private WoundService woundService;



    @GetMapping("/get-patient-names")
    public ResponseEntity<List<PatientNamesDTO>> getPatientNames() {
        return ResponseEntity.ok().body(patientService.getPatientNames());
    }

    @GetMapping("/get-patient-details")
    public ResponseEntity<?> getPatientDetails(@RequestParam String barcode) {
        try {
            PatientDetailsDTO patient = patientService.getPatientDetails(barcode);
            return ResponseEntity.ok().body(patient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patient found with barcode: " + barcode);
        }
    }

    @GetMapping("/get-patient-wounds")
    public ResponseEntity<List<PatientWoundsDTO>> getPatientWounds(@RequestParam Long id) {
        return ResponseEntity.ok().body(patientService.getPatientWoundsById(id));
    }

    @GetMapping("/get-patient-wounds-by-barcode")
    public ResponseEntity<List<PatientWoundsDTO>> getPatientWoundsByBarcode(@RequestParam String barcode) {
        return ResponseEntity.ok().body(patientService.getPatientWoundsByBarcode(barcode));
    }

    @GetMapping("/get-wound-phases")
    public ResponseEntity<List<WoundPhaseEntity>> getWoundPhases(@RequestParam Long id) {
        return ResponseEntity.ok().body(woundService.getWoundPhases(id));
    }

    @PostMapping("/add-patient")
    public ResponseEntity<String> addPatient(@RequestBody AddPatientRequest request) {
        return ResponseEntity.ok().body(patientService.addPatient(request));
    }

    @PostMapping("/add-wound")
    public ResponseEntity<String> addWound(@RequestBody AddWoundRequest request) {
        return ResponseEntity.ok().body(woundService.addWound(request));
    }

    @PostMapping("/add-wound-phase")
    public ResponseEntity<String> addWoundPhase(@RequestParam("image") MultipartFile image, @RequestBody AddWoundPhaseRequest request) {
        return ResponseEntity.ok().body(woundService.addWoundPhase(image, request));
    }

}
