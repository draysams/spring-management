package com.draysams.patientservice.mapper;

import java.time.LocalDate;

import com.draysams.patientservice.dto.PatientRequestDTO;
import com.draysams.patientservice.dto.PatientResponseDTO;
import com.draysams.patientservice.model.Patient;

public class PatientMapper {
	
	public static PatientResponseDTO toDTO(Patient patient) {
		PatientResponseDTO patientDTO = new PatientResponseDTO();
		
		patientDTO.setId(patient.getId().toString());
		patientDTO.setName(patient.getName());
		patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setEmail(patient.getEmail());
		
		return patientDTO;
	}
	
	public static Patient toModel(PatientRequestDTO patientRequestDTO) {
		Patient patient = new Patient();
		
		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
		
		return patient;
	}
	
}
