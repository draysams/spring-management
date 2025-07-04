package com.draysams.patientservice.service;

import java.util.List;

import com.draysams.patientservice.dto.PatientRequestDTO;
import com.draysams.patientservice.dto.PatientResponseDTO;
import com.draysams.patientservice.mapper.PatientMapper;
import com.draysams.patientservice.model.Patient;
import com.draysams.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
	private final PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	
	public List<PatientResponseDTO> getPatients() {
		List<Patient> patients = patientRepository.findAll();
		
		return patients.stream().map(PatientMapper::toDTO).toList();
		
	}
	
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
		Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
	
		return PatientMapper.toDTO(newPatient);
	}
}
