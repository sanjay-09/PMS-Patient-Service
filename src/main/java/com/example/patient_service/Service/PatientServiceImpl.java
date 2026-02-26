package com.example.patient_service.Service;

import com.example.patient_service.CustomException.EmailAlreadyExistsException;
import com.example.patient_service.CustomException.PatientNotFoundException;
import com.example.patient_service.Dtos.PatientRequestDto;
import com.example.patient_service.Dtos.PatientResponseDto;
import com.example.patient_service.Mapper.PatientMapper;
import com.example.patient_service.Model.Patient;
import com.example.patient_service.Repository.PatientRepository;
import com.example.patient_service.grpcClient.BillingServiceGrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;
    private BillingServiceGrpcClient billingServiceGrpcClient;

    PatientServiceImpl(PatientRepository patientRepository,BillingServiceGrpcClient billingServiceGrpcClient){
        this.patientRepository=patientRepository;
        this.billingServiceGrpcClient=billingServiceGrpcClient;
    }

    @Override
    public List<PatientResponseDto> getAllPatient() {

        List<Patient> patientsList=this.patientRepository.findAll();
        return patientsList.stream().map(patient-> PatientMapper.toDto(patient)).toList();
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        if(this.patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("this email :" + patientRequestDto.getEmail() + " is already exists");
        }
        Patient patient=PatientMapper.toModel(patientRequestDto);

        Patient managedPatient=this.patientRepository.save(patient);

        this.billingServiceGrpcClient.createBillingAccount(managedPatient.getId().toString(),managedPatient.getName(),managedPatient.getEmail());

        return PatientMapper.toDto(managedPatient);
    }

    @Override
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        Patient p=this.patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("patient with id"+id+"is not found"));
        p.setName(patientRequestDto.getName());

      if(!p.getEmail().equals(patientRequestDto.getEmail())&&this.patientRepository.existsByEmail(patientRequestDto.getEmail())){
          throw  new EmailAlreadyExistsException("this.email"+patientRequestDto.getEmail()+"is already exists");
      }
      p.setEmail(patientRequestDto.getEmail());
      p.setDateOfBirth(patientRequestDto.getDateOfBirth());

      Patient updatedPatient=this.patientRepository.save(p);
      return PatientMapper.toDto(updatedPatient);




    }




}
