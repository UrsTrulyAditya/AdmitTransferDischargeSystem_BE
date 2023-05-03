package com.stackroute.tpaMicroService.exceptions;

public class DuplicateTpaException extends RuntimeException {

	public DuplicateTpaException() {
		super("Registered already");
	}
}
