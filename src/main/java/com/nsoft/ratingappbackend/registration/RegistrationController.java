package com.nsoft.ratingappbackend.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "api/registration")
public class RegistrationController {

	private final RegistrationService registrationService;

	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {

		return registrationService.register(request);
	}
}
