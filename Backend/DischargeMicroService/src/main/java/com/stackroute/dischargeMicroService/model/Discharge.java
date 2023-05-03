package com.stackroute.dischargeMicroService.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "discharge")
public class Discharge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date dischargeDate;

	private int roomNo;

	private int patientId;

	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Discharge [id=" + id + ", dischargeDate=" + dischargeDate + ", roomNo=" + roomNo + ", patientId="
				+ patientId + ", status=" + status + "]";
	}

	
}