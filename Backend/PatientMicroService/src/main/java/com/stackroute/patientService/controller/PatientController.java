package com.stackroute.patientService.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.stackroute.patientService.model.LoginRequest;
import com.stackroute.patientService.exceptions.DuplicatePatientException;
import com.stackroute.patientService.exceptions.PatientNotFoundException;
import com.stackroute.patientService.model.Patient;
import com.stackroute.patientService.service.PatientService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) throws DuplicatePatientException {
		System.out.println("Attempting to register admin: " + patient.getEmail());
		Patient createdAdmin = patientService.registerPatient(patient);
		System.out.println("Admin registered successfully: " + createdAdmin.getEmail());
		return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginPatient(@RequestBody LoginRequest loginRequest) {

		Patient patient = patientService.loginPatient(loginRequest.getEmail(), loginRequest.getPassword());
		if (patient != null) {
			String mytoken = generateToken(patient);

			HashMap<String, Object> map = new HashMap<>();
			map.put("token", mytoken);
			map.put("message", "Success");
			map.put("account", patient);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private String generateToken(Patient login) {

		return Jwts.builder().setSubject(login.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 30000))
				.signWith(SignatureAlgorithm.HS256, "token".getBytes()).compact();
	}

	@GetMapping("/viewPatientById/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id) {
		Patient patient = patientService.viewPatientById(id);
		if (patient != null) {
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updatePatientById/{id}")
	public ResponseEntity<Patient> updatePatientById(@PathVariable("id") int id, @RequestBody Patient patient) {
		Patient updatedPatient = patientService.updatePatientById(id, patient);
		if (updatedPatient != null) {
			return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletePatientById/{id}")
	public ResponseEntity<Object> deletePatientById(@PathVariable("id") int id) {
		try {

			Boolean deleted = patientService.deletePatientById(id);
			if (deleted) {
				System.out.println(patientService.deletePatientById(id));
				return new ResponseEntity<>("deleted", HttpStatus.OK);
			}
			return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
		} catch (PatientNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/viewAllPatients")
	public ResponseEntity<Object> viewAllPatients() {
		try {
			List<Patient> admins = patientService.viewAllPatients();
			return new ResponseEntity<>(admins, HttpStatus.OK);
		} catch (PatientNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
