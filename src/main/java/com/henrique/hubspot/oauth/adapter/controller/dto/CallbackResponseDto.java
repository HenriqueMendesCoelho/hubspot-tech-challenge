package com.henrique.hubspot.oauth.adapter.controller.dto;

import com.henrique.hubspot.oauth.domain.Message;

public record CallbackResponseDto(
		String status,
		String message
) {
	public CallbackResponseDto(Message domain) {
		this(domain.getStatus(), domain.getMessage());
	}
}
