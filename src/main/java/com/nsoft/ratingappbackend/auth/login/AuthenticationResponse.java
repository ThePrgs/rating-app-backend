package com.nsoft.ratingappbackend.auth.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {

	private final String jwt;
}
