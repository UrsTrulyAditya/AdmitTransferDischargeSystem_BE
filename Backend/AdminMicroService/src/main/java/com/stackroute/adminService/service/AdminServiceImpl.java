package com.stackroute.adminService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.adminService.exception.AdminNotDeletedException;
import com.stackroute.adminService.exception.AdminNotFoundException;
import com.stackroute.adminService.exception.AdminNotUpdatedException;
import com.stackroute.adminService.exception.DuplicateAdminException;
import com.stackroute.adminService.model.Admin;
import com.stackroute.adminService.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> viewAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin register(Admin admin) {        
        Optional<Admin> optionalAdminByEmail = adminRepository.findByEmail(admin.getEmail());
        if (optionalAdminByEmail.isPresent()) {
            throw new DuplicateAdminException();
        }
        return adminRepository.save(admin);
    }


    @Override
    public Admin login(String email, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (admin.getPassword().equals(password)) {
                return admin;
            }
        }
        throw new AdminNotFoundException();
    }
    
    @Override
    public Admin updateProfile(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(admin.getAdminId());
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();
            if (admin.getName() != null) {
                existingAdmin.setName(admin.getName());
            }
            if (admin.getEmail() != null) {
                existingAdmin.setEmail(admin.getEmail());
            }
            if (admin.getPassword() != null) {
                existingAdmin.setPassword(admin.getPassword());
            }
            existingAdmin = adminRepository.save(existingAdmin);
            return existingAdmin;
        }
        throw new AdminNotUpdatedException(admin.getAdminId());
    }
    @Override
    public Admin getAdminById(int id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        } else {
            throw new AdminNotFoundException();
        }
    }
    
    @Override
    public void delete(int adminId) throws AdminNotDeletedException {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if (!optionalAdmin.isPresent()) {
            throw new AdminNotFoundException();
        }
        adminRepository.deleteById(adminId);
    }

    @Override
    public Admin updatePassword(int id, String password) throws AdminNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setPassword(password);
            return adminRepository.save(admin);
        } else {
            throw new AdminNotFoundException();
        }
    }

    
}
