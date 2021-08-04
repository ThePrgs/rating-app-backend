package com.nsoft.ratingappbackend.auth;

import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.payload.RoleResponse;
import com.nsoft.ratingappbackend.auth.payload.TokenRequest;
import java.io.IOException;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
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
			response = appUserService.singIn(request);
			if (response.getRole() != null) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
		} catch (IOException e) {
			response.setStatus("401 Unauthorized");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			response.setStatus("Something went wrong...");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
