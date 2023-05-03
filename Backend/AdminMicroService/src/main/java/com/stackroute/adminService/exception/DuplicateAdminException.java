package com.stackroute.adminService.exception;

public class DuplicateAdminException extends RuntimeException {
	
	public DuplicateAdminException() {
		super("Admin with id already exists");
	}
}
