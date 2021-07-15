package com.nsoft.ratingappbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HomeController {

	@GetMapping(path = "public")
	public String home() {
		return "Welcome to public.";
	}

	@GetMapping(path = "private")
	public String privateRoute() {
		return "You are authorized.";
	}
}
