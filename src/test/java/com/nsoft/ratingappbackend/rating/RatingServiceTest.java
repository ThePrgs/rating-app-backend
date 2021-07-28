package com.nsoft.ratingappbackend.rating;

import static org.junit.jupiter.api.Assertions.*;

import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class RatingServiceTest {

	EmojiRepository emojiRepository;
	RatingRepository ratingRepository;

	@Test
	void areDatesValid() {
		RatingService ratingService = new RatingService(ratingRepository,emojiRepository);

		boolean test = ratingService.areDatesValid(Instant.now().minusSeconds(3_000_000L), Instant.now());

		assertFalse(test);
	}
}