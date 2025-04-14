package com.henrique.hubspot.oauth.adapter.controller.impl;

import com.henrique.hubspot.oauth.adapter.controller.OAuthController;
import com.henrique.hubspot.oauth.adapter.controller.dto.AuthorizeResponseDto;
import com.henrique.hubspot.oauth.adapter.controller.dto.CallbackResponseDto;
import com.henrique.hubspot.oauth.usecase.OAuthAuthorizeUseCase;
import com.henrique.hubspot.oauth.usecase.OAuthCallbackUseCase;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OAuthControllerImpl implements OAuthController {

	private final OAuthAuthorizeUseCase oAuthAuthorizeUseCase;
	private final OAuthCallbackUseCase oAuthCallbackUseCase;

	@Override
	public AuthorizeResponseDto authorize() {
		return new AuthorizeResponseDto(oAuthAuthorizeUseCase.authorize());
	}

	@Override
	public CallbackResponseDto callback(String code) throws HubspotOAuthException {
		return new CallbackResponseDto(oAuthCallbackUseCase.execute(code));
	}
}
