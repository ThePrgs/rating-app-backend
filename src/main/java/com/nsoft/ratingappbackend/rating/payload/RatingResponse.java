package com.nsoft.ratingappbackend.rating.payload;

import com.nsoft.ratingappbackend.rating.Rating;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Client receives a RatingResponse after creating a rating
 */
@Getter
@Setter
@NoArgsConstructor
public class RatingResponse {
	private String message;
	private Rating rating;
}
