package com.henrique.hubspot.oauth.adapter.repository.rest;

import com.henrique.hubspot.oauth.domain.Token;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;

public interface OAuthRestRepositoty {

	Token exchangeCode(String code) throws HubspotOAuthException;

	Token refreshToken(String refreshToken) throws HubspotOAuthException;
}
