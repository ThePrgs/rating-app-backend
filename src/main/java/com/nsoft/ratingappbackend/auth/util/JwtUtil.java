package com.nsoft.ratingappbackend.auth.util;

import com.nsoft.ratingappbackend.appuser.AppUser;
import com.nsoft.ratingappbackend.appuser.AppUserRepository;
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


	/**
	 * @param email email to be validated
	 * @return a boolean - true if the user is verified, false if not
	 */
	@SneakyThrows
	public boolean validateToken(String email) {
		try {
			Optional<AppUser> user = appUserRepository.findByEmail(email);

			return user.isPresent();
		} catch (Exception e) {
			return false;
		}
	}
}
