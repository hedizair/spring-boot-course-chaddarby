package com.hzair.springboot.cruddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CruddemoApplication {

	// * No need to do controllers and services because we use Spring Data REST and
	// * Spring Data JPA to handles Rest CRUD operations automatically.
	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

}
