package com.stackroute.adminService.service;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stackroute.adminService.exception.AdminNotFoundException;
import com.stackroute.adminService.exception.DuplicateAdminException;
import com.stackroute.adminService.model.Admin;
import com.stackroute.adminService.repository.AdminRepository; 

public class AdminServiceTest {

	
	@InjectMocks
	AdminServiceImpl pservice;
	
	@Mock
	AdminRepository  prepo;
	
	
	Admin patient;
	
	List<Admin> patients=new ArrayList<Admin>();
	
	
	@BeforeEach
	public void setup()
	{
		 MockitoAnnotations.openMocks(this); 
    	 patient.setAdminId(10);
    	 patient.setEmail("Roman@gmail.com");
    	 patient.setName("Roman");
    	 patient.setPassword("password");
    	 
    	 
		patients.add(patient);
		
		
	}
	
	
	@Test
	public void whenAddpatientStoredsuccess() throws DuplicateAdminException
	{
		//stubbing
		Mockito.when(prepo.save(patient)).thenReturn(patient);
		
		Admin patresult=pservice.register(patient);
		
		assertEquals(patresult.getName(),"Ruban");
		
		verify(prepo,times(1)).findById(patient.getAdminId());
		verify(prepo,times(1)).save(patient);
		
	}
	
	@Test
	public void whenAddpatientFailedToStore()
	{
		
		Mockito.when(prepo.findById(10)).thenReturn(Optional.of(patient));
		
		assertThrows(DuplicateAdminException.class,()->pservice.register(patient));
		
		
		
		
	}
	
	
	@Test
	public void whenGetListViewAllObject()
	{
		Mockito.when(prepo.findAll()).thenReturn(patients);
		
	List<Admin> patientlist  =	pservice.viewAdmins();
	
	
	assertEquals(patientlist,patients);
	
	
	}
	

	
}



