package com.stackroute.patientService.exceptions;

public class DuplicatePatientException extends RuntimeException {

	public DuplicatePatientException() {
		super("Patient with id already exists");
	}
}
