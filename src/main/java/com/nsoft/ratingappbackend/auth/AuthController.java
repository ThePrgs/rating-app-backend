package com.nsoft.ratingappbackend.auth;

import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.login.AuthenticationRequest;
import com.nsoft.ratingappbackend.auth.login.AuthenticationResponse;
import com.nsoft.ratingappbackend.auth.util.JwtUtil;
import com.nsoft.ratingappbackend.payload.AuthResponse;
import com.nsoft.ratingappbackend.payload.LoginRequest;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private final JwtUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword()
			)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenUtil.createToken(authentication);
		return ResponseEntity.ok(new AuthResponse(token));
	}

}
