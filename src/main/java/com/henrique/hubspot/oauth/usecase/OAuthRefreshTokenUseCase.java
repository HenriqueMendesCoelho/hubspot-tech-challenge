package com.henrique.hubspot.oauth.usecase;

import com.henrique.hubspot.oauth.domain.Token;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;

public interface OAuthRefreshTokenUseCase {

	Token execute(String refreshToken) throws HubspotOAuthException;

}
