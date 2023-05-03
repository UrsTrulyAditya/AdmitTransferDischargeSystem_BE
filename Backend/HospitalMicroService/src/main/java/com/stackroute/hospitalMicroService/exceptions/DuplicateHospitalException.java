package com.stackroute.hospitalMicroService.exceptions;

public class DuplicateHospitalException extends RuntimeException {

	public DuplicateHospitalException() {
		super("Already added");
	}
}
