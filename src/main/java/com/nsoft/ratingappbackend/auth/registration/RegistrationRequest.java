package com.nsoft.ratingappbackend.auth.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class RegistrationRequest {

	private final String name;
	private final String email;
	private final String password;
}
