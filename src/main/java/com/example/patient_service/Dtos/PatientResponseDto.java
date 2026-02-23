package com.example.patient_service.Dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {

    private String patientId;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Date createdAt;
    private Date updatedAt;
}
