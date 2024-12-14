package com.ykeshtdar.StartP9Monolothic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@SpringBootApplication
public class mediLaboSolutionPatientHistory {

	public static void main(String[] args) {

		SpringApplication.run(mediLaboSolutionPatientHistory.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
