package com.henrique.hubspot.oauth.usecase.impl;

import com.henrique.hubspot.oauth.adapter.repository.db.TokenDBRepository;
import com.henrique.hubspot.oauth.adapter.repository.rest.OAuthRestRepositoty;
import com.henrique.hubspot.oauth.domain.Token;
import com.henrique.hubspot.oauth.usecase.OAuthRefreshTokenUseCase;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthRefreshTokenUseCaseImpl implements OAuthRefreshTokenUseCase {

	private final OAuthRestRepositoty oAuthRestRepositoty;
	private final TokenDBRepository tokenDBRepository;

	@Override
	public Token execute(String refreshToken) throws HubspotOAuthException {
		Token token = oAuthRestRepositoty.refreshToken(refreshToken);
		tokenDBRepository.deleteAll();
		tokenDBRepository.save(token);

		return token;
	}
}
