package com.stackroute.adminService.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

//import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.adminService.exception.AdminNotDeletedException;
import com.stackroute.adminService.exception.AdminNotFoundException;
import com.stackroute.adminService.exception.DuplicateAdminException;
import com.stackroute.adminService.model.Admin;
import com.stackroute.adminService.model.LoginRequest;
import com.stackroute.adminService.service.AdminService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public AdminService adminService;

	@PostMapping("/register")
	public ResponseEntity<Object> registerAdmin(@RequestBody Admin admin) {
		try {
			Admin createdAdmin = adminService.register(admin);
			return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
		} catch (DuplicateAdminException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginAdmin(@RequestBody LoginRequest loginRequest) {
	    try {
	    	Admin admin = adminService.login(loginRequest.getEmail(), loginRequest.getPassword());

	        String mytoken = generateToken(admin);

	        HashMap<String, Object> map = new HashMap<>();
	        map.put("token", mytoken);
	        map.put("account", admin);
	        return new ResponseEntity<>(map,HttpStatus.OK);
	    } catch (AdminNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}

	private String generateToken(Admin login) {
//	    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	    return Jwts.builder().setSubject(login.getEmail())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 30000))
	            .signWith(SignatureAlgorithm.HS256,"token".getBytes()).compact();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/updateProfile")
	public ResponseEntity<Object> updateAdmin(@RequestBody Admin admin) {
		try {
			Admin updatedAdmin = adminService.updateProfile(admin);
			return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
		} catch (AdminNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/updateProfile/{id}/password")
	public ResponseEntity<Object> updateAdminPasswordById(@PathVariable("id") int id, @RequestBody String password) {
	    try {
	        Admin updatedAdmin = adminService.updatePassword(id, password);
	        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
	    } catch (AdminNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}


	@GetMapping("/viewAdmin/{id}")
	public ResponseEntity<Object> viewAdmin(@PathVariable int id) {
		try {
			Admin admin = adminService.getAdminById(id);
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} catch (AdminNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAdmin(@PathVariable int id) throws AdminNotDeletedException {
		try {
			adminService.delete(id);
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} catch (AdminNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	@GetMapping("/viewAllAdmins")
	public ResponseEntity<Object> viewAdmins() {
		try {
			List<Admin> admins = adminService.viewAdmins();
			return new ResponseEntity<>(admins, HttpStatus.OK);
		} catch (AdminNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
