package com.stackroute.AdmissionMicroService.exceptions;

public class DuplicateAdmissionException extends RuntimeException {

	public DuplicateAdmissionException() {
		super("Duplicate admission");
	}
}
