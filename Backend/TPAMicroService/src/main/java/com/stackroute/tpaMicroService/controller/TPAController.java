package com.stackroute.tpaMicroService.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;


import com.stackroute.tpaMicroService.exceptions.DuplicateTpaException;
import com.stackroute.tpaMicroService.exceptions.TPANotDeletedException;
import com.stackroute.tpaMicroService.exceptions.TPANotFoundException;
import com.stackroute.tpaMicroService.model.LoginRequest;
import com.stackroute.tpaMicroService.model.TPA;
import com.stackroute.tpaMicroService.service.TPAService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping("/tpa")
public class TPAController {

	@Autowired
	private TPAService tpaService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {

		TPA tpa = tpaService.login(loginRequest.getEmail(), loginRequest.getPassword());
		TPA tpaObj = tpaService.login(tpa.getEmail(), tpa.getPassword());

		if (tpaObj != null) {
			String mytoken = generateToken(tpa);
			HashMap<String, Object> map = new HashMap<>();
			map.put("token", mytoken);
			map.put("message", "success");
			map.put("account", tpa);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			HashMap<String, String> map = new HashMap<>();
			map.put("message", "invalid");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
		}
	}

	private String generateToken(TPA login) {
		return Jwts.builder().setSubject(login.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 30000))
				.signWith(SignatureAlgorithm.HS256, "token".getBytes()).compact();
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody TPA tpa) {	
		try {
			TPA createdAdmin = tpaService.register(tpa);
			return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
		} catch (DuplicateTpaException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TPA> updateTPA(@PathVariable int id, @RequestBody TPA tpa) {

		TPA tpaObj = tpaService.updateTPA(id, tpa);

		if (tpaObj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(tpaObj);

	}

	@GetMapping("/viewAllTpas")
	public ResponseEntity<Object> viewTpas() {
	    try {
	        List<TPA> tpas = tpaService.viewTpas();
	        return new ResponseEntity<>(tpas, HttpStatus.OK);
	    } catch (TPANotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("/deleteTpa/{id}")
	public ResponseEntity<Object> deleteTpaById(@PathVariable int id) {
		try {
			tpaService.deleteTpaById(id);
			return new ResponseEntity<>("tpa deleted", HttpStatus.OK);
		} catch (TPANotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/viewTpa/{id}")
	public ResponseEntity<Object> viewTpaById(@PathVariable int id) {
		try {
			TPA tpa = tpaService.viewTpaById(id);
			if(tpa!=null) {				
				return new ResponseEntity<>(tpa, HttpStatus.OK);
			}
			return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
		} catch (TPANotDeletedException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}




}