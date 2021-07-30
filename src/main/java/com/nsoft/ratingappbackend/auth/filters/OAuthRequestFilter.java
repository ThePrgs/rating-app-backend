package com.nsoft.ratingappbackend.auth.filters;

import com.google.gson.JsonObject;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * Filter for the OAuth verification
 */
@AllArgsConstructor
@Component
@Configuration
public class OAuthRequestFilter extends OncePerRequestFilter {

	private final AppUserService appUserService;
	private final JwtUtil jwtUtil;

	/**
	 * @param request http request
	 * @param response http response
	 * @param filterChain filter chain
	 * @throws ServletException if servlet encounters difficulty
	 * @throws IOException if the access token is invalid
	 * @throws NullPointerException if there are illegal usages of the null object
	 */
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
			try {
				JsonObject json = appUserService.validateAccessToken(token);
				email = json.get("email").getAsString();
			} catch (RuntimeException e) {
				response.setStatus(401);
			} catch (IOException e) {
				response.setStatus(401);
			}
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
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
			} catch (UsernameNotFoundException e) {
				logger.error(e);
			}

		}
		filterChain.doFilter(request, response);
	}
}
