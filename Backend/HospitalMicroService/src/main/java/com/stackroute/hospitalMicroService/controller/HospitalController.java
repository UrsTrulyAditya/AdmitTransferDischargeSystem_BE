package com.stackroute.hospitalMicroService.controller;

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

import com.stackroute.hospitalMicroService.exceptions.HospitalNotFoundException;
import com.stackroute.hospitalMicroService.model.Hospital;
import com.stackroute.hospitalMicroService.service.HospitalService;

@RestController
@CrossOrigin
@RequestMapping("/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@PostMapping("/add")
	public ResponseEntity<?> addHospital(@RequestBody Hospital hospital) {

		Hospital savedHospital = hospitalService.addHospital(hospital);

		if (savedHospital == null) {
			return new ResponseEntity<>("Hospital with same name already exists", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(savedHospital, HttpStatus.CREATED);
	}

	@GetMapping("/{hospitalId}")
	public ResponseEntity<?> viewHospitalById(@PathVariable int hospitalId) {

		Hospital hospital = hospitalService.getHospitalById(hospitalId);

		if (hospital == null) {

			return new ResponseEntity<>("Hospital not found", HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<>(hospital, HttpStatus.OK);

	}

	@DeleteMapping("/{hospitalId}")
	public ResponseEntity<Object> deleteHospitalById(@PathVariable int hospitalId) {

		hospitalService.deleteHospitalById(hospitalId);

		return new ResponseEntity<>("Hospital deleted successfully", HttpStatus.OK);

	}

	@PutMapping("/{hospitalId}")
	public ResponseEntity<?> updateHospitalById(@PathVariable int hospitalId, @RequestBody Hospital hospital) {

		Hospital updatedHospital = hospitalService.updateHospitalById(hospitalId, hospital);

		if (updatedHospital == null) {

			return new ResponseEntity<>("Hospital not found", HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<>(updatedHospital, HttpStatus.OK);

	}

	@GetMapping("/viewhospitals")
	public ResponseEntity<Object> viewHospitals() throws HospitalNotFoundException {
		List<Hospital> hospitals = hospitalService.findAllHospitals();
		return new ResponseEntity<>(hospitals, HttpStatus.OK);

	}
}
