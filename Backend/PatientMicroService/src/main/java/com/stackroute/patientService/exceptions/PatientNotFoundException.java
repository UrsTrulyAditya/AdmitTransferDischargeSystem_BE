package com.stackroute.patientService.exceptions;

public class PatientNotFoundException extends RuntimeException {
	
	public PatientNotFoundException() {
		super("Patient not found");
	}
}
