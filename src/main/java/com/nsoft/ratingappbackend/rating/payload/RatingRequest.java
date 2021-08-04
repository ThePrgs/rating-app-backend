package com.nsoft.ratingappbackend.rating.payload;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Client sends this request to post a rating.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingRequest {

	/**
	 * Id of an emoji.
	 */
	@NotNull
	private Long emojiId;
}
