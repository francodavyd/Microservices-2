package com.francodavyd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ViviendaSvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViviendaSvApplication.class, args);
	}

}
