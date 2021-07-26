package com.nsoft.ratingappbackend.auth;

import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.login.AuthenticationRequest;
import com.nsoft.ratingappbackend.auth.login.AuthenticationResponse;
import com.nsoft.ratingappbackend.auth.registration.RegistrationRequest;
import com.nsoft.ratingappbackend.auth.registration.RegistrationService;
import com.nsoft.ratingappbackend.auth.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final AppUserService appUserService;
	private final RegistrationService registrationService;
	private final JwtUtil jwtTokenUtil;

	/*/@PostMapping("register")
	public String register(@RequestBody RegistrationRequest request) {

		return registrationService.register(request);
	}/*/

	/*/@PostMapping("authenticate")
	public ResponseEntity<?> createAuthenticationToken(
		@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {

			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password.", e);
		}

		final UserDetails userDetails = appUserService
			.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}/*/

}
