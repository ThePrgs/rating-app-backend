package com.nsoft.ratingappbackend.security.config;

import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Pusher - a WebSockets solution.
 */
@Configuration
@AllArgsConstructor
public class PusherConfig {

	private final AppProperties appProperties;

	/**
	 * Method configures Pusher and returns a Singleton Pusher Bean.
	 *
	 * @return Pusher
	 */
	@Bean
	public Pusher getPusher() {
		Pusher pusher = new Pusher(appProperties.getPusherAppId(), appProperties.getPusherKey(),
			appProperties.getPusherSecret());
		pusher.setCluster(appProperties.getPusherCluster());
		pusher.setEncrypted(true);
		return pusher;
	}
}
