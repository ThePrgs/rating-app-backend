package com.nsoft.ratingappbackend.auth.authpayload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TokenRequest {

	private String accessToken;
}
