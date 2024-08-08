package com.system.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.system.springboot.model"})
public class StudentManagementSystemApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
			
	}
}
