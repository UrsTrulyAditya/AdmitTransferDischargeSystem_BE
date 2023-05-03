
package com.stackroute.AdmissionMicroService.service;

import java.util.List;

import javax.validation.Valid;

import com.stackroute.AdmissionMicroService.exceptions.AdmissionNotFoundException;
import com.stackroute.AdmissionMicroService.model.Admission;


public interface AdmissionService {

	List<Admission> getAllAdmissions();

	Admission getAdmissionById(int id);

	Admission updateAdmission(int id, Admission admission);

	Admission createAdmission( Admission admission);

	boolean deleteAdmitRequest(int admissionId);

	Admission getAdmissionByPatientEmail(String patientEmail);

	Admission updateAdmissionStatusById(int admissionId, String status) throws AdmissionNotFoundException;

	Admission updateAdmissionByPatientId(int patientId, @Valid Admission admission) throws AdmissionNotFoundException;

}