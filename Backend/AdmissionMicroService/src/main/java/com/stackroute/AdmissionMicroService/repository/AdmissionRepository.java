package com.stackroute.AdmissionMicroService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 

import org.springframework.stereotype.Repository; 


import com.stackroute.AdmissionMicroService.model.Admission;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {

	Optional<Admission> findByPatientEmail(String string);

	Admission findByPatientId(int patientId);
 

 

} 