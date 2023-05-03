package com.stackroute.hospitalMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.hospitalMicroService.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

	Hospital findByName(String name); 

} 
