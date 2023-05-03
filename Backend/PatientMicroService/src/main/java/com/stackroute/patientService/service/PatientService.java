package com.stackroute.patientService.service;

import java.util.List;

import com.stackroute.patientService.model.Patient;

public interface PatientService {

	Patient updatePatientById(int id, Patient patient);

	boolean deletePatientById(int id);

	Patient registerPatient(Patient patient);

	Patient loginPatient(String email, String password);

	Patient viewPatientById(int id);

	List<Patient> viewAllPatients();

	
}
