package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import com.nsoft.ratingappbackend.rating.payload.RatingRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingResponse;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service class for rating.
 *
 * @see Rating
 */
@Service
@AllArgsConstructor
public class RatingService {

	private final RatingRepository ratingRepository;
	private final EmojiRepository emojiRepository;

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
				response.setMessage("All ratings between " + request.getStartDate() + " and "
					+ request.getEndDate());
				response.setRatings(ratingsBetweenDates);
			} else {
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
			URL url = new URL(
				"https://hooks.slack.com/services/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");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			String jsonInputString = "{\"text\": \"There was less then 10 rating today\"}";

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			try (BufferedReader br = new BufferedReader(
				new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
				StringBuilder response = new StringBuilder();
				String responseLine;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
			}

			con.disconnect();
		}
	}
}
