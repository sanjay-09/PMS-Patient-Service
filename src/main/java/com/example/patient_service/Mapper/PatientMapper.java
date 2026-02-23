package com.example.patient_service.Mapper;

import com.example.patient_service.Dtos.PatientRequestDto;
import com.example.patient_service.Dtos.PatientResponseDto;
import com.example.patient_service.Model.Patient;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){

        return PatientResponseDto.builder().
                patientId(patient.getId().toString()).
                name(patient.getName()).
                email(patient.getEmail()).
                createdAt(patient.getCreatedAt()).
                updatedAt(patient.getUpdatedAt()).
                dateOfBirth(patient.getDateOfBirth()).build();

    }

    public static Patient toModel(PatientRequestDto patientRequestDto){
        return Patient.builder()
                .name(patientRequestDto.getName())
                .email(patientRequestDto.getEmail())
                .password(patientRequestDto.getPassword())
                .dateOfBirth(patientRequestDto.getDateOfBirth())
                .registeredDate(patientRequestDto.getRegisteredDate())
                .build();
    }
}
