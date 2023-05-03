package com.stackroute.hospitalMicroService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.hospitalMicroService.model.Hospital;
import com.stackroute.hospitalMicroService.repository.HospitalRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	public Hospital addHospital(Hospital hospital) {
	    Hospital existingHospital = hospitalRepository.findByName(hospital.getName());
	    if (existingHospital != null) {
	        return null; 
	    }
	    return hospitalRepository.save(hospital); // create and save new hospital
	}

	@Override
	public Hospital getHospitalById(int hospitalId) {

		Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);

		return optionalHospital.orElse(null);

	}

	@Override
	public void deleteHospitalById(int hospitalId) {

		hospitalRepository.deleteById(hospitalId);

	}

	@Override
	public Hospital updateHospitalById(int hospitalId, Hospital hospital) {

		Optional<Hospital> optionalHospital = hospitalRepository.findById(hospitalId);

		if (optionalHospital.isPresent()) {

			Hospital existingHospital = optionalHospital.get();

			existingHospital.setName(hospital.getName());

			existingHospital.setLocation(hospital.getLocation());

			return hospitalRepository.save(existingHospital);

		}

		return null;

	}

	@Override
	public List<Hospital> findAllHospitals() {
		
		return hospitalRepository.findAll();
	}

}
