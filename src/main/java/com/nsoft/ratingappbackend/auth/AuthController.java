package com.nsoft.ratingappbackend.auth;

import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.payload.RoleResponse;
import com.nsoft.ratingappbackend.auth.payload.TokenRequest;
import java.io.IOException;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController - an authorization rest controller.
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping(path = "/api/auth")
public class AuthController {

	private final AppUserService appUserService;

	/**
	 * API endpoint - checks for the access token integrity (whether it is from Google or not).
	 *
	 * @param request request that contains Google access token
	 * @return RoleResponse with http status code and user role
	 */
	@PostMapping
	public ResponseEntity<RoleResponse> authenticate(@Valid @RequestBody TokenRequest request) {
		RoleResponse response = new RoleResponse();
		try {
			response = appUserService.signIn(request);
			if (response.getRole() != null) {
				log.info("User successfully authenticated.");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.warn("User not found. Unauthorized!");
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
		} catch (IOException e) {
			log.error("Check for access token validity! It's either expired or invalid.");
			response.setStatus("401 Unauthorized");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			log.error("Something went wrong...");
			response.setStatus("Something went wrong...");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * API endpoint - revokes Google access token.
	 *
	 * @param request request with Google access token.
	 * @return ResponseEntity with message and HttpStatus code.
	 */
	@PostMapping("/revoke")
	public ResponseEntity<String> revoke(@Valid @RequestBody TokenRequest request){
		try {
			log.info("Revoking access token...");
			return new ResponseEntity<>(appUserService.revokeAccessToken(request), HttpStatus.OK);
		}
		catch (IOException e){
			log.error("Could not revoke access token. Check for validity!");
			return new ResponseEntity<>("400 Bad Request", HttpStatus.BAD_REQUEST);
		}
	}
}
