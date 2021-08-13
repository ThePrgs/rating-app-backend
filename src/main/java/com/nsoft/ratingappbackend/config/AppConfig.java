package com.nsoft.ratingappbackend.config;

import com.nsoft.ratingappbackend.security.config.AppProperties;
import com.pusher.rest.Pusher;
import com.slack.api.Slack;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the application.
 */
@Configuration
@AllArgsConstructor
public class AppConfig {

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


	@Bean
	public Slack getSlack(){
		return Slack.getInstance();
	}
}
