package com.nsoft.ratingappbackend.rating;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import com.nsoft.ratingappbackend.rating.payload.RatingRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingResponse;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesResponse;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import com.nsoft.ratingappbackend.security.config.AppProperties;
import org.junit.jupiter.api.Test;

class RatingServiceTest {

	EmojiRepository emojiRepository = mock(EmojiRepository.class);
	RatingRepository ratingRepository = mock(RatingRepository.class);
	AppProperties appProperties = mock(AppProperties.class);

	RatingService ratingService = new RatingService(ratingRepository,emojiRepository, appProperties);

	@Test
	void areDatesValid() {

		boolean test = ratingService.areDatesValid(Instant.now().minusSeconds(3_000_000L), Instant.now());

		assertFalse(test);
	}

	@Test
	void getRatingsBetweenDates() {
		RatingsBetweenDatesRequest request = new RatingsBetweenDatesRequest();
		request.setStartDate(Instant.now().minusSeconds(3000));
		request.setEndDate(Instant.now());

		RatingsBetweenDatesResponse response = ratingService.getRatingsBetweenDates(request);

		assertNotNull(response);


	}

	@Test
	void createRating() {
		List<Emoji> emojis = Arrays.asList(
			new Emoji(2L, "HAPPY", "rgb(65, 179, 233)", "https://res.cloudinary.com/dxlyytkww/image/upload/v1627541876/RatingApp")
		);

		when(emojiRepository.findById(2L)).thenReturn(emojis.stream().findAny());
		RatingRequest request = new RatingRequest();
		request.setEmojiId(2L);

		RatingResponse response = ratingService.createRating(request);
		long responseEmojiId = response.getRating().getEmojiId().getId();
		long requestEmojiId = request.getEmojiId();

		assertEquals(responseEmojiId,requestEmojiId);
	}
}