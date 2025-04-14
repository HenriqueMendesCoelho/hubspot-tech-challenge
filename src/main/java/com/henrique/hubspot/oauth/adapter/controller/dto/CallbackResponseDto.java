package com.henrique.hubspot.oauth.adapter.controller.dto;

import com.henrique.hubspot.oauth.domain.Callback;

public record CallbackResponseDto(
		String status,
		String message
) {
	public CallbackResponseDto(Callback domain) {
		this(domain.getStatus(), domain.getMessage());
	}
}
