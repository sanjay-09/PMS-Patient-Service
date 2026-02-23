package com.example.patient_service.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends  BaseModel{

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false,unique = true)
    private String email;


    private String password;

    private LocalDate registeredDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;
}
