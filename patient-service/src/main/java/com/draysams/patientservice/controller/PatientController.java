package com.draysams.patientservice.controller;

import java.util.List;

import com.draysams.patientservice.dto.PatientResponseDTO;
import com.draysams.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
	private final PatientService patientService;
	
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
	public ResponseEntity<List<PatientResponseDTO>> getPatients() {
		List<PatientResponseDTO> patients = patientService.getPatients();
		return ResponseEntity.ok().body(patients);
	}
}
