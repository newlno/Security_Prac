package com.example.Security_Prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SecurityPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityPracApplication.class, args);
	}

}
