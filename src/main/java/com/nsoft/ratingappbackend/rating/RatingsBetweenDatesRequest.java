package com.nsoft.ratingappbackend.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingsBetweenDatesRequest {

	@NotNull
	private Instant firstDate;
	@NotNull
	private Instant endDate;
}
