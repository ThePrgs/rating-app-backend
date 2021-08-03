package com.nsoft.ratingappbackend.rating.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;

/**
 * RatingRequest contains emojiId
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingRequest {

	@NotNull
	private Long emojiId;
}
