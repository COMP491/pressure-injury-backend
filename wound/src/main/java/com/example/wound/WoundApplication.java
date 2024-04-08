package com.example.wound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.example.wound.WoundApplication.class, args);
	}

}
