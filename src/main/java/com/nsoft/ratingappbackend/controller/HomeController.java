package com.nsoft.ratingappbackend.controller;

import com.nsoft.ratingappbackend.security.config.AppProperties;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@RequestMapping("/api")
@AllArgsConstructor
public class HomeController {

	AppProperties appProperties;

	@GetMapping(path = "public")
	public String home() {
		return "Welcome to public.";
	}

	@GetMapping(path = "private")
	public String privateRoute() {
		return "You are authorized.";
	}

	@GetMapping(path = "googletest")
	public String googleTest() {
		return "Eureka";
	}

	@GetMapping("test")
	public String propertiesTest(){ return appProperties.getTokenSecret();}
}
