package com.draysams.patientservice.controller;

import java.util.List;
import java.util.UUID;

import com.draysams.patientservice.dto.PatientRequestDTO;
import com.draysams.patientservice.dto.PatientResponseDTO;
import com.draysams.patientservice.dto.validator.CreatePatientValidationGroup;
import com.draysams.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {
	private final PatientService patientService;
	
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
	@Operation(summary = "Get Patients")
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		List<PatientResponseDTO> patients = patientService.getPatients();
		return ResponseEntity.ok().body(patients);
	}
	
	@PostMapping
	@Operation(summary = "Add a new patient")
	public ResponseEntity<PatientResponseDTO> createPatient(@Validated(
			{ Default.class, CreatePatientValidationGroup.class }) @RequestBody PatientRequestDTO patientRequestDTO) {
		PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
		return ResponseEntity.ok().body(patientResponseDTO);
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Update an existing patient")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
			@Validated({ Default.class }) @RequestBody PatientRequestDTO patientRequestDTO) {
		PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
		return ResponseEntity.ok().body(patientResponseDTO);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete an existing patient")
	public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
		patientService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}
}
