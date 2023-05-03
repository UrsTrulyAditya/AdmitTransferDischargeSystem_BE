package com.stackroute.adminService.exception;

public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException() {
        super("Admin not found");
    }

}
