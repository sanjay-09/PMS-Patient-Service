package com.example.patient_service.Controller;


import com.example.patient_service.Dtos.PatientRequestDto;
import com.example.patient_service.Dtos.PatientResponseDto;
import com.example.patient_service.Dtos.validators.CreatePatientValidatorGroup;
import com.example.patient_service.Service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private PatientService patientService;

    PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<?> getAllPatient(){

       List<PatientResponseDto> patientResponseDto=this.patientService.getAllPatient();
        return ResponseEntity.status(HttpStatus.OK).body(patientResponseDto);

    }

    @PostMapping("/patient")
    public ResponseEntity<?> createPatient(@Validated({Default.class, CreatePatientValidatorGroup.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto=this.patientService.createPatient(patientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponseDto);

    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto){
        PatientResponseDto patientResponseDto=this.patientService.updatePatient(id,patientRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponseDto);
    }
}
