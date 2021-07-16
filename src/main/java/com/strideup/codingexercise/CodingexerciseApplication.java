package com.strideup.codingexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CodingexerciseApplication {

	public static void main(String[] args) { SpringApplication.run(CodingexerciseApplication.class, args); }

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
