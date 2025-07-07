package com.draysams.patientservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.draysams.patientservice.dto.PatientRequestDTO;
import com.draysams.patientservice.dto.PatientResponseDTO;
import com.draysams.patientservice.exception.EmailAlreadyExistsException;
import com.draysams.patientservice.exception.PatientNotFoundException;
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

		if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
		}
		Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
	
		return PatientMapper.toDTO(newPatient);
	}
	
	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " +  id));
		
		if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
			throw new EmailAlreadyExistsException("A patient with this email already exists" + patientRequestDTO.getEmail());
		}
		
		patient.setName(patientRequestDTO.getName());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
		
		Patient updatedPatient = patientRepository.save(patient);
		return PatientMapper.toDTO(updatedPatient);
	}
}



