package com.henrique.hubspot.oauth.usecase;

import com.henrique.hubspot.oauth.domain.Callback;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;

public interface OAuthCallbackUseCase {

	Callback execute(String code) throws HubspotOAuthException;

}
