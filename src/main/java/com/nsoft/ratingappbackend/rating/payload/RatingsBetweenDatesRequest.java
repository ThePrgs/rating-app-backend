package com.nsoft.ratingappbackend.rating.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Request for all ratings between two dates. Difference between dates can't be more than 30 days.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingsBetweenDatesRequest {

	@NotNull
	private Instant startDate;
	@NotNull
	private Instant endDate;
}
