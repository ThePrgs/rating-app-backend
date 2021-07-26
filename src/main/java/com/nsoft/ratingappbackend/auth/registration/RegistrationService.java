package com.nsoft.ratingappbackend.auth.registration;

import com.nsoft.ratingappbackend.appuser.AppUser;
import com.nsoft.ratingappbackend.appuser.AppUserRole;
import com.nsoft.ratingappbackend.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

	private final AppUserService appUserService;

}
