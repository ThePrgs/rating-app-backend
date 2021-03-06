package com.nsoft.ratingappbackend.appuser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nsoft.ratingappbackend.auth.payload.RoleResponse;
import com.nsoft.ratingappbackend.auth.payload.TokenRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import javax.net.ssl.HttpsURLConnection;
import com.nsoft.ratingappbackend.security.config.AppProperties;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A service class for AppUser.
 *
 * @see AppUser
 */
@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {

	private static final String USER_NOT_FOUND_MSG = "User with that email not found";
	private static final String OK = "200 OK";
	private static final String UNAUTHORIZED = "401 UNAUTHORIZED";
	private final AppUserRepository appUserRepository;
	private final AppProperties appProperties;


	/**
	 * Loads user using their email.
	 *
	 * @param email email to be checked against the database.
	 * @return UserDetails of the user.
	 * @throws UsernameNotFoundException if the email is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("Loading user with mail " + email + "...");
		return appUserRepository
			.findByEmail(email)
			.orElseThrow(
				() ->
					new UsernameNotFoundException(
						String.format(USER_NOT_FOUND_MSG, email)));

	}

	/**
	 * Validates Google access token.
	 *
	 * @param token Google access token to be verified.
	 * @return JsonObject which contains user data if token's integrity is valid.
	 * @throws IOException if the token is invalid.
	 */
	public JsonObject validateAccessToken(String token) throws IOException {
		URL url = new URL(
			appProperties.getGoogleValidateTokenLink() + token);
		log.info("Opening connection to " + appProperties.getGoogleValidateTokenLink() + token + "...");
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		JsonObject json = JsonParser.parseReader(in).getAsJsonObject();
		in.close();
		con.disconnect();
		log.info("Access token successfully validated.");
		return json;
	}

	/**
	 * Method revokes Google access token.
	 *
	 * @param token Google access token to be revoked.
	 * @return String.
	 * @throws IOException in case of invalid access token.
	 */
	public String revokeAccessToken(TokenRequest token) throws IOException {
		URL url = new URL(appProperties.getGoogleRevokeTokenLink() + token.getAccessToken());
		log.info("Opening connection to " + appProperties.getGoogleRevokeTokenLink() + token.getAccessToken() + "...");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Length", "0");
		con.setRequestProperty("Accept", "*/*");
		con.setDoOutput(true);
		con.connect();

		con.getOutputStream().close();
		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		in.close();
		con.disconnect();
		log.info("Access token successfully revoked.");
		return OK;
	}

	/**
	 *
	 *
	 * @param request contains Google access token.
	 * @return ??200 OK?? if the user is in the database, or ??401 UNAUTHORIZED?? if he is not.
	 * @throws IOException if the token is invalid.
	 */
	@SneakyThrows
	public RoleResponse signIn(TokenRequest request) throws IOException {
		JsonObject json = validateAccessToken(request.getAccessToken());
		String mail = json.get("email").getAsString();
		Optional<AppUser> user = appUserRepository.findByEmail(mail);

		RoleResponse response = new RoleResponse();
		if(user.isPresent()) {
			log.info(user.get().getEmail() + " is present. Setting up response...");
			AppUserRole userRole = user.get().getAppUserRole();
			response.setStatus(OK);
			response.setRole(userRole);
			return response;
		} else {
			log.warn("401! Email is unauthorized!");
			response.setStatus(UNAUTHORIZED);
			return response;
		}
	}


}
