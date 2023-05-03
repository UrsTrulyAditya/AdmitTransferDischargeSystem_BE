package com.stackroute.dischargeMicroService.controller;

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

import com.stackroute.dischargeMicroService.model.Discharge;
import com.stackroute.dischargeMicroService.service.DischargeService;

@RestController
@CrossOrigin
@RequestMapping("/discharge")
public class DischargeController {

	@Autowired
	private DischargeService dischargeService;


	@GetMapping("getDischargeDetails/{id}")
	public Discharge getDischargeDetailsById(@PathVariable int id) {

		return dischargeService.getDischargeDetailsById(id);

	}
	
	@GetMapping("getDischargeDetailsByPatientId/{patientId}")
	public Discharge getDischargeDetailsByPatientId(@PathVariable int patientId) {
	return dischargeService.getDischargeDetailsByPatientId(patientId);
	}

	@PostMapping("/addDischargeDetails")
	public ResponseEntity<?> addDischargeDetails(@RequestBody Discharge discharge) {

	    Discharge existingDischarge = dischargeService.getDischargeById(discharge.getPatientId());
	    if (existingDischarge != null) {
	        return new ResponseEntity<>("Discharge for same patient already exists", HttpStatus.BAD_REQUEST);
	    }

	    Discharge savedDischarge = dischargeService.saveDischarge(discharge);

	    return new ResponseEntity<>(savedDischarge, HttpStatus.CREATED);
	}

	
	
	
	@PutMapping("updateDischargeDetails/{id}")
	public Discharge updateDischargeDetailsById(@PathVariable int id, @RequestBody Discharge discharge) {

		return dischargeService.updateDischarge(id, discharge);
	}

	

	@DeleteMapping("deletegetDischargeDetails/{id}")
	public void deleteDischargeDetailsById(@PathVariable int id) {

		dischargeService.deleteDischargeById(id);

	}

	
	@GetMapping("/viewAllDischargeDetails")
	public ResponseEntity<Object> viewHospitals()  {
		List<Discharge> allData = dischargeService.getAllData();
		return new ResponseEntity<>(allData, HttpStatus.OK);

	}

}