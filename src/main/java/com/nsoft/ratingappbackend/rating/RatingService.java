package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import com.nsoft.ratingappbackend.rating.payload.RatingRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingResponse;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for rating
 */
@Service
@AllArgsConstructor
public class RatingService {

	private final RatingRepository ratingRepository;
	private final EmojiRepository emojiRepository;

	/**
	 * Method takes request containing emojiId and creates a rating with that id
	 *
	 * @param request emojiID
	 * @return boolean
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

	public RatingsBetweenDatesResponse getRatingsBetweenDates(RatingsBetweenDatesRequest request) {
		RatingsBetweenDatesResponse response = new RatingsBetweenDatesResponse();
		try {

			List<Rating> ratingsBetweenDates =  ratingRepository.findAllByDateBetween(request.getFirstDate(), request.getEndDate());
			if(!ratingsBetweenDates.isEmpty()) {
				response.setMessage("All ratings between " + request.getFirstDate() + " and " + request.getEndDate());
				response.setRatings(ratingsBetweenDates);
			} else {
				response.setMessage("No ratings found between " + request.getFirstDate() + " and " + request.getEndDate() + ".");
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return response;
	}

	public boolean areDatesValid(Instant firstDate, Instant lastDate) {

		// is first date before the second, and is difference between dates <= than 30 days
		return firstDate.isBefore(lastDate) && (firstDate.until(lastDate, ChronoUnit.DAYS) <= 30);
	}
}
