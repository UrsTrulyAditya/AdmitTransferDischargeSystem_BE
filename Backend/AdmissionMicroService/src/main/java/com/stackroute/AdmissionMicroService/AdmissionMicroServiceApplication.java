package com.stackroute.AdmissionMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdmissionMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdmissionMicroServiceApplication.class, args);
	}

}
