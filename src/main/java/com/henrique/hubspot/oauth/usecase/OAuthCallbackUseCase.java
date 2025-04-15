package com.henrique.hubspot.oauth.usecase;

import com.henrique.hubspot.oauth.domain.Message;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;

public interface OAuthCallbackUseCase {

	Message execute(String code) throws HubspotOAuthException;

}
