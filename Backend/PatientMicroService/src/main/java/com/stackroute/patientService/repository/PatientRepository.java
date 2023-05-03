package com.stackroute.patientService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.patientService.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByEmail(String email);

    void deleteByPatientId(String patientId);

    Optional<Patient> findByPatientId(String patientId);
}
