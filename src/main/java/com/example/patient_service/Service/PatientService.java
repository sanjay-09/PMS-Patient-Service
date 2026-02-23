package com.example.patient_service.Service;

import com.example.patient_service.Dtos.PatientRequestDto;
import com.example.patient_service.Dtos.PatientResponseDto;
import com.example.patient_service.Model.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {

     List<PatientResponseDto> getAllPatient();

     PatientResponseDto createPatient(PatientRequestDto patientRequestDto);

     PatientResponseDto updatePatient(UUID id,PatientRequestDto patientRequestDto);




}
