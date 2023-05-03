package com.stackroute.adminService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;

	private String password;

	private String name;

	private String email;

	public Admin() {
		super();
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

	public Admin(int adminId, String password, String name, String email) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

}