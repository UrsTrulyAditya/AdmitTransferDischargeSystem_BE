package com.stackroute.patientService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.patientService.exceptions.PatientNotFoundException;
import com.stackroute.patientService.model.Patient;
import com.stackroute.patientService.repository.PatientRepository; 

public class PatientServiceTest {

	
	@InjectMocks
	PatientImpl pservice;
	
	@Mock
	PatientRepository prepo;
	
	
	Patient patient;
	
	List<Patient> patients=new ArrayList<Patient>();
	
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
		patient=new Patient();
		patient.setPatientId(10);
		patient.setName("Ruban");
		patient.setAge(32);
		patient.setProblem("Headache");

		patient.setEmail("test@gmail.com");
		patient.setMobile("Headache");
		patient.setPassword("password");
		patient.setRoomNo(102);
		
		
		patients.add(patient);
		
		
	}
	
	
	@Test
	public void whenAddpatientStoredsuccess() throws PatientNotFoundException
	{
		//stubbing
		Mockito.when(prepo.save(patient)).thenReturn(patient);
		
		Patient patresult=pservice.registerPatient(patient);
		
		assertEquals(patresult.getPatientId(),"Ruban");
		
		verify(prepo,times(1)).findById(patient.getPatientId());
		verify(prepo,times(1)).save(patient);
		
	}
	
	@Test
	public void whenAddpatientFailedToStore()
	{
		
		Mockito.when(prepo.findById(10)).thenReturn(Optional.of(patient));
		
		assertThrows(PatientNotFoundException.class,()->pservice.registerPatient(patient));
		
		
		
		
	}
	
	
	@Test
	public void whenGetListViewAllObject()
	{
		Mockito.when(prepo.findAll()).thenReturn(patients);
		
	List<Patient> patientlist=pservice.viewAllPatients();
	
	
	assertEquals(patientlist,patients);
	
	
	}
	
	@Test
	public void whenDeleteThenSuccess() throws PatientNotFoundException
	{
		Mockito.when(prepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		
	boolean result=		pservice.deletePatientById(patient.getPatientId());
		
	assertTrue(result);
	
	verify(prepo,times(1)).deleteById(patient.getPatientId());
	
	
	}
	
	@Test
	
	public void whenDeleteThenFailed()
	{
		 Mockito.when(prepo.findById(patient.getPatientId())).thenReturn(Optional.empty());
		 
		 assertThrows(PatientNotFoundException.class,()-> pservice.deletePatientById(patient.getPatientId()));
		 
			verify(prepo,times(0)).deleteById(patient.getPatientId());
	}
	
}



