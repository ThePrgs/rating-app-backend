package com.nsoft.ratingappbackend.registration;

import com.nsoft.ratingappbackend.appuser.AppUser;
import com.nsoft.ratingappbackend.appuser.AppUserRole;
import com.nsoft.ratingappbackend.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

	private final AppUserService appUserService;

	public String register(RegistrationRequest request) {

		return appUserService.singUpUser(
			new AppUser(
				request.getFirstName(),
				request.getLastName(),
				request.getEmail(),
				request.getPassword(),
				AppUserRole.USER));
	}
}
