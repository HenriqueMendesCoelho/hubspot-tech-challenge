package com.henrique.hubspot.oauth.adapter.repository.rest.dto;

import com.henrique.hubspot.oauth.domain.Token;

public record TokenResponseDto(
		String accessToken,
		String refreshToken,
		Long expiresIn
) {

	public Token toDomain() {
		return Token.builder().accessToken(accessToken).refreshToken(refreshToken).expiresIn(expiresIn).build();
	}

}
