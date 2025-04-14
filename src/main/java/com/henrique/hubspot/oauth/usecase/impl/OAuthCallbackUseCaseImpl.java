package com.henrique.hubspot.oauth.usecase.impl;

import com.henrique.hubspot.oauth.adapter.repository.db.TokenDBRepository;
import com.henrique.hubspot.oauth.adapter.repository.rest.OAuthRestRepositoty;
import com.henrique.hubspot.oauth.domain.Callback;
import com.henrique.hubspot.oauth.domain.Token;
import com.henrique.hubspot.oauth.usecase.OAuthCallbackUseCase;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthCallbackUseCaseImpl implements OAuthCallbackUseCase {

	private final OAuthRestRepositoty oAuthRestRepositoty;
	private final TokenDBRepository tokenDBRepository;

	@Override
	public Callback execute(String code) throws HubspotOAuthException {
		Token result = oAuthRestRepositoty.exchangeCode(code);
		tokenDBRepository.save(result);

		return Callback.builder().status("sucess").message("Access token received and stored successfully").build();
	}
}
