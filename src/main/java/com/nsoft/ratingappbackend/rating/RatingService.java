package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import com.nsoft.ratingappbackend.rating.payload.RatingRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingResponse;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesResponse;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import com.nsoft.ratingappbackend.security.config.AppProperties;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service class for rating.
 *
 * @see Rating
 */
@Service
@AllArgsConstructor
@Slf4j
public class RatingService {

	private final RatingRepository ratingRepository;
	private final EmojiRepository emojiRepository;
	private final AppProperties appProperties;
	private final Slack slack;


	/**
	 * Method takes request containing emojiId and creates a rating with that id.
	 *
	 * @param request request with emoji id.
	 * @return RatingResponse with a message and newly created rating.
	 */
	public RatingResponse createRating(RatingRequest request) {
		RatingResponse response = new RatingResponse();
		Optional<Emoji> emoji = emojiRepository.findById(request.getEmojiId());
		if (emoji.isPresent()) {

			Rating rating = new Rating(emoji.get(), Instant.now());
			ratingRepository.save(rating);

			response.setMessage("New rating created!");
			response.setRating(rating);
			return response;
		}

		response.setMessage("400 Bad Request! Invalid emoji identification!");
		return response;
	}

	/**
	 * Method finds all ratings between two dates.
	 *
	 * @param request request made to get all the ratings between two dates.
	 * @return a list of ratings between two dates.
	 */
	public RatingsBetweenDatesResponse getRatingsBetweenDates(RatingsBetweenDatesRequest request) {
		RatingsBetweenDatesResponse response = new RatingsBetweenDatesResponse();
		try {

			List<Rating> ratingsBetweenDates = ratingRepository.findAllByDateBetween(
				request.getStartDate(), request.getEndDate());
			if (!ratingsBetweenDates.isEmpty()) {
				log.info("Successfully found ratings between" + request.getStartDate() + " and " + request.getEndDate());
				response.setMessage("All ratings between " + request.getStartDate() + " and "
					+ request.getEndDate());
				response.setRatings(ratingsBetweenDates);
			} else {
				log.info("No ratings found between" + request.getStartDate() + " and " + request.getEndDate());
				response.setMessage("No ratings found between " + request.getStartDate() + " and "
					+ request.getEndDate() + ".");
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return response;
	}

	/**
	 * Method checks if the dates are valid.
	 *
	 * @param firstDate first date of the request.
	 * @param lastDate  last date of the request.
	 * @return a boolean - true if the first date is before last and difference between is no more
	 * than 31 days; false otherwise.
	 */
	public boolean areDatesValid(Instant firstDate, Instant lastDate) {

		log.info("Validating dates!");
		// is first date before the second, and is difference between dates <= than 31 days
		return firstDate.isBefore(lastDate) && (firstDate.until(lastDate, ChronoUnit.DAYS) <= 31);
	}

	/**
	 * Method sends daily rating reports to Slack if there was less than 10 ratings.
	 */
	@Scheduled(cron = "0 59 23 * * MON-SUN")
	@SneakyThrows
	public void sendSlackReport() {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime morningDateTime = LocalDateTime.now();
		morningDateTime = morningDateTime.minusHours(localDateTime.getHour())
			.minusMinutes(localDateTime.getMinute()).minusSeconds(localDateTime.getSecond());
		List<Rating> list = ratingRepository.findAllByDateBetween(
			morningDateTime.toInstant(ZoneOffset.UTC), localDateTime.toInstant(ZoneOffset.UTC));

		if ((long) list.size() < 10) {
			log.info("Sending scheduled slack report! Ratings lower then 10");
			String webhookUrl = appProperties.getSlackReportLink();
			String payload = "{\"text\":\"There has been less than 10 ratings today!\"}";
			WebhookResponse response = slack.send(webhookUrl,payload);
			log.info(response.toString());

		}
	}
}
