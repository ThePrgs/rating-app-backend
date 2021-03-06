package com.nsoft.ratingappbackend.ratingsettings.payload;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response contains current rating settings
 */
@Getter
@Setter
@NoArgsConstructor
public class RatingSettingsResponse {

	private String message;
	private RatingSettings ratingSettings;
}
