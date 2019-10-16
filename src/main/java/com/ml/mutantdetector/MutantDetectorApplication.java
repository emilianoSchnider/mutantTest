package com.ml.mutantdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MutantDetectorApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MutantDetectorApplication.class, args);
	}
}
