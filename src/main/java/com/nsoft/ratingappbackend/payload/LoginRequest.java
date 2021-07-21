package com.nsoft.ratingappbackend.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

}
