package com.nsoft.ratingappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RatingAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingAppBackendApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	}
}
