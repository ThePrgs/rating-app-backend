package com.nsoft.ratingappbackend.appuser;

import com.nsoft.ratingappbackend.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for AppUser
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

	private static final String USER_NOT_FOUND_MSG = "User with that email not found";
	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Method checks if the user exists
	 * @param email email of the user
	 * @return UserDetails
	 * @throws UsernameNotFoundException if the user is not found
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository
			.findByEmail(email)
			.orElseThrow(
				() ->
					new UsernameNotFoundException(
						String.format(USER_NOT_FOUND_MSG, email)));
		return UserPrincipal.create(appUser);
	}

	/**
	 * Adds new user into the repository
	 * @param appUser user information
	 * @return String
	 */
	public String singUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (!userExists) {
			String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
			appUser.setPassword(encodedPassword);
			appUserRepository.save(appUser);
			return "Successful sing up";
		}
		return "User already exists";
	}
}
