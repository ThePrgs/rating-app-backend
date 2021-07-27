package com.nsoft.ratingappbackend.rating;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RatingsBetweenDatesResponse {

	private String message;
	private List<Rating> ratings;
}
