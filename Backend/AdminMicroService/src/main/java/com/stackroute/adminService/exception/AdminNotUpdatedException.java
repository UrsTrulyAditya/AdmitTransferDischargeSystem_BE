package com.stackroute.adminService.exception;

public class AdminNotUpdatedException extends RuntimeException {

	 public AdminNotUpdatedException(int id) {
	        super("Admin details not updated");
	    }
}
