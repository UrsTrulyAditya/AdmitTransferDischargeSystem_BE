package com.stackroute.dischargeMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DischargeMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DischargeMicroServiceApplication.class, args);
	}

}
