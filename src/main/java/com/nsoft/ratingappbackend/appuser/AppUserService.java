package com.nsoft.ratingappbackend.appuser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nsoft.ratingappbackend.auth.payload.RoleResponse;
import com.nsoft.ratingappbackend.auth.payload.TokenRequest;
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
import org.springframework.stereotype.Service;

/**
 * A service class for AppUser
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

	private static final String USER_NOT_FOUND_MSG = "User with that email not found";
	private final AppUserRepository appUserRepository;


	/**
	 * @param email email to be checked against the database
	 * @return UserDetails of the user
	 * @throws UsernameNotFoundException if the email is not found
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository
			.findByEmail(email)
			.orElseThrow(
				() ->
					new UsernameNotFoundException(
						String.format(USER_NOT_FOUND_MSG, email)));

	}


	/**
	 * @param token Google access token to be verified.
	 * @return JsonObject which contains user data if token's integrity is valid.
	 * @throws IOException if the token is invalid.
	 */
	public JsonObject validateAccessToken(String token) throws IOException {
		URL url = new URL(
			"https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + token);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		JsonObject json = JsonParser.parseReader(in).getAsJsonObject();
		in.close();
		return json;
	}

	/**
	 * @param request contains Google access token
	 * @return 200 OK if the user is in the database, or 401 UNAUTHORIZED if he is not.
	 * @throws IOException if the token is invalid.
	 */
	@SneakyThrows
	public RoleResponse singIn(TokenRequest request) throws IOException {
		JsonObject json = validateAccessToken(request.getAccessToken());
		String mail = json.get("email").getAsString();
		Optional<AppUser> user = appUserRepository.findByEmail(mail);

		RoleResponse response = new RoleResponse();
		if (user.isPresent()) {
			AppUserRole userRole = user.get().getAppUserRole();
			response.setStatus("200 OK");
			response.setRole(userRole);
			return response;
		} else {
			response.setStatus("401 UNAUTHORIZED");
			return response;
		}
	}


}
