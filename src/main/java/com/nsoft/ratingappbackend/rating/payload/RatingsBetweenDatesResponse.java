package com.nsoft.ratingappbackend.rating.payload;

import com.nsoft.ratingappbackend.rating.Rating;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RatingsBetweenDatesResponse {

	private String message;
	private List<Rating> ratings;
}
