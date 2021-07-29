package com.nsoft.ratingappbackend.rating.payload;

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
	private Instant startDate;
	@NotNull
	private Instant endDate;
}