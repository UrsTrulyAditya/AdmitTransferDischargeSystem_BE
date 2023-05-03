package com.stackroute.dischargeMicroService.service;

import java.util.List;

import com.stackroute.dischargeMicroService.model.Discharge;

public interface DischargeService { 

    Discharge saveDischarge(Discharge discharge); 

    Discharge getDischargeById(int id); 

    void deleteDischargeById(int id); 

    List<Discharge> getDischargesByPatientId(int patientId); 


    Discharge updateDischarge(int id, Discharge discharge);

	List<Discharge> getAllData();
 

	Discharge getDischargeDetailsByPatientId(int patientId);

	Discharge getDischargeDetailsById(int id); 

} 