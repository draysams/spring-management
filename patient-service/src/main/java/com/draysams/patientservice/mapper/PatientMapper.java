package com.draysams.patientservice.mapper;

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
	
}
