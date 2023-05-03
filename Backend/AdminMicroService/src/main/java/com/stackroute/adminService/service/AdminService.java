package com.stackroute.adminService.service;

import java.util.List;

import com.stackroute.adminService.exception.AdminNotDeletedException;
import com.stackroute.adminService.model.Admin;

public interface AdminService {
	Admin register(Admin admin);

	Admin updateProfile(Admin admin);
	
	List<Admin> viewAdmins();

	Admin login(String email, String password);

	void delete(int adminId) throws AdminNotDeletedException;

	Admin getAdminById(int adminId);

	Admin updatePassword(int id, String password);


}
