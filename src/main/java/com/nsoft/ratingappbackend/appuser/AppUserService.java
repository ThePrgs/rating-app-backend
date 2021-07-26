package com.nsoft.ratingappbackend.appuser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nsoft.ratingappbackend.auth.registration.RegistrationRequest;
import com.nsoft.ratingappbackend.security.config.ApplicationProperties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import javax.net.ssl.HttpsURLConnection;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
public class AppUserService implements UserDetailsService{

	private static final String USER_NOT_FOUND_MSG = "User with that email not found";
	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ApplicationProperties applicationProperties;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository
			.findByEmail(email)
			.orElseThrow(
				() ->
					new UsernameNotFoundException(
						String.format(USER_NOT_FOUND_MSG, email)));
		return appUser;
	}

	/**
	 * Adds new user into the repository
	 * @param appUser user information
	 * @return String
	 */
	public String singUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (!userExists) {
			appUserRepository.save(appUser);
			return "Successful sing up";
		}
		return "User already exists";
	}

	public JsonObject validateAccessToken(String token) throws IOException {
		try {
			URL url = new URL(
				"https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" + token);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
			JsonObject json = JsonParser.parseReader(in).getAsJsonObject();
			in.close();
			return json;
		}catch (Exception e){
			return new JsonObject();
		}

	}

	@SneakyThrows
	public RoleResponse singIn(RegistrationRequest request)throws IOException {
		System.out.println(request.getAccessToken());
		JsonObject json = validateAccessToken(request.getAccessToken());
		String mail = json.get("email").getAsString();
		Optional<AppUser> user = appUserRepository.findByEmail(mail);

		RoleResponse response = new RoleResponse();
		if(user.isPresent()){
			AppUserRole userRole = user.get().getAppUserRole();
			response.setStatus("200 OK");
			response.setRole(userRole);
			return response;
		}
		else{
			AppUser newUser = new AppUser(
				mail,
				AppUserRole.USER
			);
			appUserRepository.save(newUser);
			response.setRole(AppUserRole.USER);
			response.setStatus("200 OK");
			return response;
		}
	}


}
