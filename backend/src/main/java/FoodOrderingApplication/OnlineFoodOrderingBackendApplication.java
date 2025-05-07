package com.project.Online.Food.Ordering.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class OnlineFoodOrderingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderingBackendApplication.class, args);
		System.out.println("Running...");
	}
	
}
