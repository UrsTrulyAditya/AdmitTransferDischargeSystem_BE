package com.stackroute.patientService.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.patientService.exceptions.PatientNotFoundException; 
import com.stackroute.patientService.model.Patient;
import com.stackroute.patientService.service.PatientService; 


@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

	@InjectMocks
	PatientController pcontroller;

	@MockBean
	PatientService pservice;

	MockMvc mockmvc;

	Patient patient = new Patient();

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(pcontroller).build();
		patient = new Patient();
		patient.setPatientId(1);
		patient.setName("John");
		patient.setAge(35);
		patient.setEmail("john@gmail.com");
		patient.setMobile("9876543210");
		patient.setPassword("john123");
		patient.setProblem("Fever");
		patient.setRoomNo(101);
	}

	@Test
	public void whenPostObjectSuccess() throws JsonProcessingException, Exception {

		Mockito.when(pservice.registerPatient(patient)).thenReturn(patient);

		mockmvc.perform(MockMvcRequestBuilders.post("/patient/register").contentType(MediaType.APPLICATION_JSON)
				.content(convertObject(patient))).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void whenPostObjectThenFailed() throws JsonProcessingException, Exception {

		Mockito.when(pservice.registerPatient(any())).thenThrow(PatientNotFoundException.class);

		mockmvc.perform(MockMvcRequestBuilders.post("/patient/register").contentType(MediaType.APPLICATION_JSON)
				.content(convertObject(patient))).andExpect(MockMvcResultMatchers.status().isConflict());

	}

	private String convertObject(Object obj) throws JsonProcessingException {
		ObjectMapper objmapper = new ObjectMapper();
		return objmapper.writeValueAsString(obj);
	}

	@Test

	public void deletePatientSuccess() throws Exception {
		Mockito.when(pservice.deletePatientById(100)).thenReturn(true);

		mockmvc.perform(MockMvcRequestBuilders.delete("/patient/deletePatientById/100").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());

	}

	@Test

	public void deletePatientFailure() throws Exception {
		Mockito.when(pservice.deletePatientById(100)).thenThrow(PatientNotFoundException.class);

		mockmvc.perform(MockMvcRequestBuilders.delete("/patient/deletePatientById/100").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());

	}

}