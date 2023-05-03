package com.stackroute.dischargeMicroService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.dischargeMicroService.model.Discharge;
import com.stackroute.dischargeMicroService.repository.DischargeRepository;

@Service
public class DischargeServiceImpl implements DischargeService {

	@Autowired
	private DischargeRepository dischargeRepository;

	@Override
	public Discharge saveDischarge(Discharge discharge) {

		return dischargeRepository.save(discharge);

	}

	@Override
	public Discharge getDischargeById(int id) {

		List<Discharge> dischargeList =  dischargeRepository.findByPatientId(id);
		System.out.println(dischargeList);
		for (Discharge discharge : dischargeList) {
	        if (discharge.getPatientId() == id) {
	            return discharge;
	        }
	    }
		return null;
	}

	@Override
	public Discharge getDischargeDetailsById(int id) {

		Discharge discharge =  dischargeRepository.findById(id);
		
		return discharge;
	}
	@Override
	public void deleteDischargeById(int id) {

		dischargeRepository.deleteById(id);

	}

	@Override
	public List<Discharge> getDischargesByPatientId(int patientId) {

		return dischargeRepository.findByPatientId(patientId);

	}

	@Override
	public Discharge updateDischarge(int id, Discharge discharge) {

		Discharge dischargeToUpdate = dischargeRepository.findById(id);

		if (dischargeToUpdate != null) {
			dischargeToUpdate.setDischargeDate(discharge.getDischargeDate());
			dischargeToUpdate.setRoomNo(discharge.getRoomNo());
			dischargeToUpdate.setPatientId(discharge.getPatientId());
			dischargeToUpdate.setStatus(discharge.getStatus());
			dischargeRepository.save(dischargeToUpdate);
		}

		return dischargeToUpdate;

	}

	@Override
	public List<Discharge> getAllData() {
		return dischargeRepository.findAll();
	}

	 

	@Override
	public Discharge getDischargeDetailsByPatientId(int patientId) {
	    return dischargeRepository.findDischargeByPatientId(patientId);
	}


}