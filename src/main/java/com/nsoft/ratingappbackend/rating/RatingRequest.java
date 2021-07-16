package com.nsoft.ratingappbackend.rating;

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

	private Long emojiId;
}
