package com.nsoft.ratingappbackend.auth.filters;

import com.google.gson.JsonObject;
import com.nsoft.ratingappbackend.appuser.AppUserRepository;
import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.util.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@AllArgsConstructor
@Component
@Configuration
public class JwtRequestFilter extends OncePerRequestFilter {

	private final AppUserService appUserService;
	private final JwtUtil jwtUtil;
	private final AppUserRepository appUserRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain)
		throws ServletException, IOException, NullPointerException {

		final String authorizationHeader = request.getHeader("Authorization");

		String email = null;
		String token = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7);
				JsonObject json = appUserService.validateAccessToken(token);
				try {
					email = json.get("email").getAsString();
				}catch (RuntimeException e){
					response.setStatus(401);
				}
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = appUserService.loadUserByUsername(email);

			if (jwtUtil.validateToken(token)) {
				var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					user,
					null,
					user.getAuthorities()
				);

				usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext()
					.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
