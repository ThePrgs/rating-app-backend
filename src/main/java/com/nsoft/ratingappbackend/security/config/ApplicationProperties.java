package com.nsoft.ratingappbackend.security.config;


import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationProperties {

	@Value("${app.auth.token-secret}")
	private String tokenSecret;

	@Value("${app.auth.token-expiration-msec}")
	private String expirationMsec;

	@Value("${app.oauth2.authorized-redirect-uris}")
	private List<String> authorizedRedirectUris;
}
