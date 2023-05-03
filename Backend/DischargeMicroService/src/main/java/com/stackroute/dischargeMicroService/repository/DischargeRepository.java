package com.stackroute.dischargeMicroService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.dischargeMicroService.model.Discharge;


public interface DischargeRepository extends JpaRepository<Discharge, Integer> { 

    Discharge save(Discharge discharge); 

    Discharge findById(int id); 

    void deleteById(int id); 

    List<Discharge> findByPatientId(int patientId); 

    List<Discharge> findByStatus(String status);

	Discharge findDischargeByPatientId(int patientId);
    
   	
} 