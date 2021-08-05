package com.nsoft.ratingappbackend.ratingsettings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
import java.util.Arrays;
import java.util.List;
import com.nsoft.ratingappbackend.security.config.AppProperties;
import org.junit.jupiter.api.Test;

class RatingSettingsServiceTest {

	RatingSettingsRepository ratingSettingsRepository = mock(RatingSettingsRepository.class);
	AppProperties appProperties = mock(AppProperties.class);

	RatingSettingsService ratingSettingsService = new RatingSettingsService(ratingSettingsRepository, appProperties);

	@Test
	void getRatingSettings() {
		List<RatingSettings> settings = Arrays.asList(
			new RatingSettings( 3, 3, "Test")
		);

		when(ratingSettingsRepository.findById(1L)).thenReturn(settings.stream().findFirst());
		RatingSettingsResponse response =ratingSettingsService.getRatingSettings();
		int responseNum = response.getRatingSettings().getNumOfEmoticons();


		assertEquals(3, responseNum);

	}
}