package com.stackroute.AdmissionMicroService.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.AdmissionMicroService.exceptions.AdmissionNotFoundException;
import com.stackroute.AdmissionMicroService.exceptions.DuplicateAdmissionException;
import com.stackroute.AdmissionMicroService.model.Admission;
import com.stackroute.AdmissionMicroService.service.AdmissionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admission")
public class AdmissionController {

	@Autowired
	private AdmissionService admissionService;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/request")
	public ResponseEntity<?> createAdmission(@RequestBody Admission admission) {

		try {
			Admission createdAdmission = admissionService.createAdmission(admission);
			return new ResponseEntity<>(createdAdmission, HttpStatus.CREATED);
		} catch (DuplicateAdmissionException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@PutMapping("/update/{admissionId}")
	public ResponseEntity<?> updateAdmission(@PathVariable(value = "admissionId") int admissionId,

			@Valid @RequestBody Admission admission) {

		Admission updatedAdmission = admissionService.updateAdmission(admissionId, admission);

		return new ResponseEntity<>(updatedAdmission, HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/updateAdmission/{patientId}")
	public ResponseEntity<?> updateAdmissionByPatientId(@PathVariable(value = "patientId") int patientId,
			@Valid @RequestBody Admission admission) throws AdmissionNotFoundException {
		Admission updatedAdmission = admissionService.updateAdmissionByPatientId(patientId, admission);
		return ResponseEntity.ok(updatedAdmission);
	}

//	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/updateStatus/{admissionId}")
	public ResponseEntity<?> updateAdmissionStatus(@PathVariable(value = "admissionId") int admissionId,
			@RequestBody Admission admission) throws AdmissionNotFoundException {
		Admission updatedAdmission = admissionService.updateAdmissionStatusById(admissionId, admission.getStatus());
		return new ResponseEntity<>(updatedAdmission, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAdmitRequest/{admissionId}")
	public ResponseEntity<?> deleteAdmissionRequest(@PathVariable(value = "admissionId") int admissionId) {
		boolean deleted = admissionService.deleteAdmitRequest(admissionId);
		if (deleted) {
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("admission record not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{patientEmail}")
	public ResponseEntity<?> getAdmissionByPatientEmail(@PathVariable(value = "patientEmail") String patientEmail) {

		Admission admission = admissionService.getAdmissionByPatientEmail(patientEmail);

		if (admission == null) {
			return new ResponseEntity<>("Request not found", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok().body(admission);

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllAdmissions() {

		List<Admission> admissions = admissionService.getAllAdmissions();

		return ResponseEntity.ok().body(admissions);

	}

}
