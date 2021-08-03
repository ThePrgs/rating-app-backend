package com.nsoft.ratingappbackend.rating.payload;

import com.nsoft.ratingappbackend.rating.Rating;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Client receives a response with all ratings between two dates.
 *
 * @see RatingsBetweenDatesRequest
 */
@Getter
@Setter
public class RatingsBetweenDatesResponse {

	private String message;
	private List<Rating> ratings;
}
