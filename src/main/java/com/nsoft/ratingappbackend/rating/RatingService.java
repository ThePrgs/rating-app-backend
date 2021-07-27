package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
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
	public boolean createRating(RatingRequest request) {
		Optional<Emoji> emoji = emojiRepository.findById(request.getEmojiId());
		if (emoji.isPresent()) {

			Rating rating = new Rating(emoji.get(), Instant.now());

			ratingRepository.save(rating);
			return true;
		}

		return false;
	}

	public List<Rating> getRatingsBetweenDates(RatingsBetweenDates request) {
		try {

			return ratingRepository.findAllByDateBetween(request.getFirstDate(), request.getEndDate());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}
}
