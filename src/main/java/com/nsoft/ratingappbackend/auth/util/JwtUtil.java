package com.nsoft.ratingappbackend.auth.util;

import com.google.gson.JsonObject;
import com.nsoft.ratingappbackend.appuser.AppUser;
import com.nsoft.ratingappbackend.appuser.AppUserRepository;
import com.nsoft.ratingappbackend.appuser.AppUserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * Utility class used for token validity check.
 */
@Service
@AllArgsConstructor
public class JwtUtil {

	private final AppUserRepository appUserRepository;
	private final AppUserService appUserService;


	/**
	 * @param token token to be validated
	 * @return a boolean - true if the user is verified, false if not
	 */
	@SneakyThrows
	public boolean validateToken(String token) {
		try {
			JsonObject json = appUserService.validateAccessToken(token);
			String mail = json.get("email").getAsString();
			Optional<AppUser> user = appUserRepository.findByEmail(mail);

			return user.isPresent();
		} catch (Exception e) {
			return false;
		}
	}
}
