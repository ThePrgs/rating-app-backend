package com.nsoft.ratingappbackend.rating.payload;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
