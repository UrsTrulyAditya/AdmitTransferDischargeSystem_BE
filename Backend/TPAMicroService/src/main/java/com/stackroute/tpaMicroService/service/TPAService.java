package com.stackroute.tpaMicroService.service;

import java.util.List;

import com.stackroute.tpaMicroService.model.TPA;

public interface TPAService { 

    TPA login(String email, String password); 

    TPA register(TPA tpa); 

    TPA updateTPA(int id, TPA tpa);

	List<TPA> viewTpas();

	void deleteTpaById(int id);

	TPA viewTpaById(int id); 

} 