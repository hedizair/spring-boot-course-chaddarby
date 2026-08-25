package com.hzair.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// To scan additional packages, we can use the scanBasePackages attribute of the @SpringBootApplication annotation. Ex :
/* @SpringBootApplication(
	scanBasePackages = {
		"com.hzair.springcoredemo",
		"com.hzair.util"
	}
) */
@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
