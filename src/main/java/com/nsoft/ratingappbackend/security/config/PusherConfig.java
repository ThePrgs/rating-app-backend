package com.nsoft.ratingappbackend.security.config;

import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class PusherConfig {

	private final AppProperties appProperties;

	@Bean
	public Pusher getPusher() {
		Pusher pusher = new Pusher(appProperties.getPusherAppId(), appProperties.getPusherKey(),
			appProperties.getPusherSecret());
		pusher.setCluster(appProperties.getPusherCluster());
		pusher.setEncrypted(true);
		return pusher;
	}
}
