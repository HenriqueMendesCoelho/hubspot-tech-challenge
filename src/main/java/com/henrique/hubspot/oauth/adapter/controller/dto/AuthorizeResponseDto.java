package com.henrique.hubspot.oauth.adapter.controller.dto;

import com.henrique.hubspot.oauth.domain.Authorize;

public record AuthorizeResponseDto(String url) {

	public AuthorizeResponseDto(Authorize domain) {
		this(domain.getUrl());
	}
}
