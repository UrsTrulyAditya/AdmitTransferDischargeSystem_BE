package com.stackroute.hospitalMicroService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hospitalId;

	private String name;

	private String location;

	public Hospital() {
	}

	public Hospital(int hospitalId, String name, String location) {

		this.hospitalId = hospitalId;

		this.name = name;

		this.location = location;

	}

	public int getHospitalId() {

		return hospitalId;

	}

	public void setHospitalId(int hospitalId) {

		this.hospitalId = hospitalId;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getLocation() {

		return location;

	}

	public void setLocation(String location) {

		this.location = location;

	}

}
