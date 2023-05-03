package com.stackroute.adminService.controller;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.adminService.exception.AdminNotFoundException;
import com.stackroute.adminService.exception.DuplicateAdminException;
import com.stackroute.adminService.model.Admin;
import com.stackroute.adminService.service.AdminService; 

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class AdminControllerTest {
	
	@InjectMocks
	AdminController  pcontroller;
	
	@MockBean
	AdminService pservice;
	
	
     MockMvc mockmvc;
     
     Admin patient=new Admin();
     
     @BeforeEach
     public void init()
     {
    	 MockitoAnnotations.openMocks(this);
    	 mockmvc=MockMvcBuilders.standaloneSetup(pcontroller).build();
    	 patient.setAdminId(10);
    	 patient.setEmail("Roman@gmail.com");
    	 patient.setName("Roman");
    	 patient.setPassword("password");
     }
     
     
     @Test
     public void whenPostObjectSuccess() throws JsonProcessingException, Exception
     {
    	 
    	 Mockito.when(pservice.register(patient)).thenReturn(patient);
    	 
    	 
    	 mockmvc.perform(MockMvcRequestBuilders.post("/admin/register")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(patient)))
    			 .andExpect(MockMvcResultMatchers.status().isCreated());
    	 
     }
     
     
     @Test
     public void whenPostObjectThenFailed() throws JsonProcessingException, Exception
     {
    	 
    	 Mockito.when(pservice.register(any())).thenThrow(DuplicateAdminException.class);
    	 
    	 
    	 mockmvc.perform(MockMvcRequestBuilders.post("/admin/register")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(patient)))
    			 .andExpect(MockMvcResultMatchers.status().isConflict());
    	 
     }
     
     
     private String convertObject(Object obj) throws JsonProcessingException
     {
    	 ObjectMapper objmapper=new ObjectMapper();
    	return objmapper.writeValueAsString(obj);
     }
     
   
     
}









