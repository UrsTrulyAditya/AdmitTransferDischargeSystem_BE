package com.stackroute.AdmissionMicroService.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.AdmissionMicroService.exceptions.AdmissionNotFoundException;
import com.stackroute.AdmissionMicroService.exceptions.DuplicateAdmissionException;
import com.stackroute.AdmissionMicroService.model.Admission;
import com.stackroute.AdmissionMicroService.repository.AdmissionRepository;
@Service
public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	private AdmissionRepository admissionRepository;

	
	@Override
	public Admission createAdmission(Admission admission) {
		Optional<Admission> existingHospital = admissionRepository.findByPatientEmail(admission.getPatientEmail());
	    System.out.println(existingHospital.isPresent());
		if (existingHospital.isPresent()) {
	        throw new DuplicateAdmissionException(); 
	    }
	    return admissionRepository.save(admission); // create and save new hospital
	}
	@Override
	public Admission updateAdmission(int admissionId, Admission admission) {

		Optional<Admission> optionalAdmission = admissionRepository.findById(admissionId);

		if (optionalAdmission.isPresent()) {

			admission.setAdmissionId(admissionId);

			return admissionRepository.save(admission);

		}
		return null;

	}

	@Override
	public boolean deleteAdmitRequest(int admissionId) {
	    Optional<Admission> optionalAdmission = admissionRepository.findById(admissionId);
	    if (optionalAdmission.isPresent()) {
	        admissionRepository.deleteById(admissionId);
	        return true;
	    }
	    return false;
	}

	@Override

	public Admission getAdmissionById(int admissionId) {

		Optional<Admission> optionalAdmission = admissionRepository.findById(admissionId);

		if (optionalAdmission.isPresent()) {

			return optionalAdmission.get();

		} else {

			return null;

		}

	}

	@Override
	public List<Admission> getAllAdmissions() {

		return admissionRepository.findAll();

	}
	
	@Override
	public Admission getAdmissionByPatientEmail(String patientEmail) {
	    List<Admission> admissions = admissionRepository.findAll();
	    for (Admission admission : admissions) {
	        if (admission.getPatientEmail().equals(patientEmail)) {
	            return admission;
	        }
	    }
	    return null; // or throw an exception if the admission is not found
	}


	  
	@Override
	public Admission updateAdmissionStatusById(int admissionId, String status) throws AdmissionNotFoundException {
		Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() -> new AdmissionNotFoundException());

        admission.setStatus(status);
        return admissionRepository.save(admission);
	}
	@Override
	public Admission updateAdmissionByPatientId(int patientId, @Valid Admission admission) throws AdmissionNotFoundException {
	    Admission existingAdmission = admissionRepository.findByPatientId(patientId);
	    if (existingAdmission == null) {
	        throw new AdmissionNotFoundException();
	    }
	    existingAdmission.setStatus(admission.getStatus());
	    existingAdmission.setRoomNo(admission.getRoomNo());
	    existingAdmission.setDischargeDate(admission.getDischargeDate());
	    return admissionRepository.save(existingAdmission);
	}


}
