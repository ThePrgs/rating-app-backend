package com.nsoft.ratingappbackend.security.config;

import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.filters.JwtRequestFilter;
import com.nsoft.ratingappbackend.security.RestAuthenticationEntryPoint;
import com.nsoft.ratingappbackend.security.oauth2.CustomOAuth2UserService;
import com.nsoft.ratingappbackend.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.nsoft.ratingappbackend.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.nsoft.ratingappbackend.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AppUserService appUserService;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	private final HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository;
	private final JwtRequestFilter jwtRequestFilter;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.userDetailsService(appUserService)
			.passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf().disable()
			.formLogin().disable()
			.authorizeRequests()
			.antMatchers("/api/private","/api/auth/**", "/api/oauth2/**")
			.authenticated()
			.anyRequest()
			.permitAll()
			.and()
			.oauth2Login().defaultSuccessUrl("/api/private")
			.authorizationEndpoint()
			.baseUri("/oauth2/authorize")
			.authorizationRequestRepository(cookieAuthorizationRequestRepository)
			.and()
			.redirectionEndpoint()
			.baseUri("/login/oauth2/code/google")
			.and()
			.userInfoEndpoint()
			.userService(customOAuth2UserService)
			.and()
			.successHandler(oAuth2AuthenticationSuccessHandler)
			.failureHandler(oAuth2AuthenticationFailureHandler);

		// Add our custom Token based authentication filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
