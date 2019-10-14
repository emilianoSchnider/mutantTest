package com.ml.mutantdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.mutantdetector.configuration.AppConfiguration;

@SpringBootApplication
@RestController
public class MutantDetectorApplication {

	public static void main(String[] args) {
		
		AppConfiguration.InitializeRepository();
		
		SpringApplication.run(MutantDetectorApplication.class, args);
	}

	@GetMapping("/")
	  public String hello() {
	    return "hello world!";
	  }
}
