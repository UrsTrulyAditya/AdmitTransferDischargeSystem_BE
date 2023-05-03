package com.stackroute.hospitalMicroService.service;

import java.util.List;

import com.stackroute.hospitalMicroService.model.Hospital;

public interface HospitalService { 

    Hospital addHospital(Hospital hospital); 

    Hospital getHospitalById(int hospitalId); 

    void deleteHospitalById(int hospitalId); 

    Hospital updateHospitalById(int hospitalId, Hospital hospital);

	List<Hospital> findAllHospitals(); 

} 