package com.stackroute.AdmissionMicroService.model;


import javax.persistence.*;

@Entity
@Table(name = "admissions")
public class Admission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int admissionId;

	private String status;
	
	private String name;
	
	private String problem;

	private int patientId;
	  
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	private int mobile;
	
	private String patientEmail;

	private int insuranceId;
	
	private int hospitalId;
	
	private int roomNo;

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	private String admissionDate;

	private String dischargeDate;

	private int tpaId;
	

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}



	public Admission() {

	}

	public Admission(int admissionId, String status, int patientId, int insuranceId, int hospitalId,
			String admissionDate, String dischargeDate, int tpaId) {

		this.admissionId = admissionId;

		this.status = status;

		this.patientId = patientId;

		this.insuranceId = insuranceId;

		this.hospitalId = hospitalId;

		this.admissionDate = admissionDate;

		this.dischargeDate = dischargeDate;

		this.tpaId = tpaId;
		
		this.roomNo = roomNo;

	}

	public int getAdmissionId() {

		return admissionId;

	}

	public void setAdmissionId(int admissionId) {

		this.admissionId = admissionId;

	}

	public String getStatus() {

		return status;

	}

	public void setStatus(String status) {

		this.status = status;

	}

	public int getPatientId() {

		return patientId;

	}

	public void setPatientId(int patientId) {

		this.patientId = patientId;

	}

	public int getInsuranceId() {

		return insuranceId;

	}

	public void setInsuranceId(int insuranceId) {

		this.insuranceId = insuranceId;

	}

	public int getHospitalId() {

		return hospitalId;

	}

	public void setHospitalId(int hospitalId) {

		this.hospitalId = hospitalId;

	}

	public String getAdmissionDate() {

		return admissionDate;

	}

	public void setAdmissionDate(String admissionDate) {

		this.admissionDate = admissionDate;

	}

	public String getDischargeDate() {

		return dischargeDate;

	}

	public void setDischargeDate(String dischargeDate) {

		this.dischargeDate = dischargeDate;

	}

	public int getTpaId() {

		return tpaId;

	}

	public void setTpaId(int tpaId) {

		this.tpaId = tpaId;

	}

}