package com.example.patient_service.Dtos;

import com.example.patient_service.Dtos.validators.CreatePatientValidatorGroup;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String password;


    @NotNull
    private LocalDate dateOfBirth;

    @NotNull(groups = CreatePatientValidatorGroup.class)
    private LocalDate registeredDate;




}
