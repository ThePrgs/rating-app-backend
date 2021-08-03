package com.nsoft.ratingappbackend.auth.payload;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Client sends this payload to check access token integrity.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenRequest {

	@NotNull
	private String accessToken;
}
