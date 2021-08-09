package com.nsoft.ratingappbackend.rating.payload;

import com.nsoft.ratingappbackend.rating.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Client receives a RatingResponse after posting a rating.
 *
 * @see RatingRequest
 */
@Getter
@Setter
@NoArgsConstructor
public class RatingResponse {

	/**
	 * An informative message.
	 */
	private String message;

	/**
	 * Rating.
	 */
	private Rating rating;
}
