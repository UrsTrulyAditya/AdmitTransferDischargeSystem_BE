package com.stackroute.hospitalMicroService.exceptions;

public class HospitalNotFoundException extends Exception {

	public HospitalNotFoundException() {
		super("Not found");
	}
}
