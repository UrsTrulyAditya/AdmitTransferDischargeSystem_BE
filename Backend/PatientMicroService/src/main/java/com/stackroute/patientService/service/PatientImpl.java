package com.stackroute.patientService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.patientService.exceptions.DuplicatePatientException;
import com.stackroute.patientService.model.Patient;
import com.stackroute.patientService.repository.PatientRepository;

@Service
public class PatientImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    @Override
    public Patient registerPatient(Patient patient) {   
        Optional<Patient> optionalPatientByEmail = patientRepository.findByEmail(patient.getEmail());
        if (optionalPatientByEmail.isPresent()) {
            throw new DuplicatePatientException();
        }
        return patientRepository.save(patient);
    }

    @Override
    public Patient loginPatient(String email, String password) {
        Optional<Patient> optionalPatientByEmail = patientRepository.findByEmail(email);
        if (optionalPatientByEmail.isPresent()) {
            Patient patient = optionalPatientByEmail.get();
            if (patient.getPassword().equals(password)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public Patient viewPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public Patient updatePatientById(int id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return null;
        }
        existingPatient.setPassword(patient.getPassword());
        existingPatient.setName(patient.getName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setProblem(patient.getProblem());
        existingPatient.setMobile(patient.getMobile());
        existingPatient.setRoomNo(patient.getRoomNo());
        existingPatient.setEmail(patient.getEmail());
        return patientRepository.save(existingPatient);
    }

    @Override
    public boolean deletePatientById(int id) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return false;
        }
        patientRepository.delete(existingPatient);
        return true;
    }

	@Override
	public List<Patient> viewAllPatients() {
		return patientRepository.findAll();
	}



}
