package com.stackroute.tpaMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TpaMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpaMicroServiceApplication.class, args);
	}

}
