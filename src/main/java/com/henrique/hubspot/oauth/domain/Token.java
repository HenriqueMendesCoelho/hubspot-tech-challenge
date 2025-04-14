package com.henrique.hubspot.oauth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

	private Long id;
	private String accessToken;
	private String refreshToken;
	private Long expiresIn;

}
