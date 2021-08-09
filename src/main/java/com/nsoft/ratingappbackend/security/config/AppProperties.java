package com.nsoft.ratingappbackend.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * AppProperties class acts as a getter for custom variables stored in application.properties.
 */
@Component
@Getter
public class AppProperties {

	/**
	 * Pusher app id.
	 */
	@Value("${app.pusher.app-id}")
	private String pusherAppId;

	/**
	 * Pusher key.
	 */
	@Value("${app.pusher.key}")
	private String pusherKey;

	/**
	 * Pusher secret.
	 */
	@Value("${app.pusher.secret}")
	private String pusherSecret;

	/**
	 * Pusher cluster.
	 */
	@Value("${app.pusher.cluster}")
	private String pusherCluster;
}
