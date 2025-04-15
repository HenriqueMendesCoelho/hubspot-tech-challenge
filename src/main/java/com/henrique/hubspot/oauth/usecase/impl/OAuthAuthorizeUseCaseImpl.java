package com.henrique.hubspot.oauth.usecase.impl;

import com.henrique.hubspot.oauth.domain.Authorize;
import com.henrique.hubspot.oauth.usecase.OAuthAuthorizeUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class OAuthAuthorizeUseCaseImpl implements OAuthAuthorizeUseCase {

	private static final String HUBSPOT_AUTHORIZATION_URL = "https://app.hubspot.com/oauth/authorize";
	private static final String REDIRECT_URI = "http://localhost:8080/api/hubspot/callback";

	@Value("${hubspot.clientid}")
	private String clientId;

	@Value("${hubspot.clientsecret}")
	private String clientSecret;

	@Override
	public Authorize authorize() {
		String encodedRedirectUri = URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8);
		String encodedScopes = URLEncoder.encode(
				"crm.objects.contacts.write crm.schemas.contacts.write crm.objects.contacts.read",
				StandardCharsets.UTF_8);
		String url = UriComponentsBuilder.fromUriString(HUBSPOT_AUTHORIZATION_URL)
				.queryParam("client_id", clientId)
				.queryParam("redirect_uri", encodedRedirectUri)
				.queryParam("scope", encodedScopes)
				.build()
				.toUriString();

		return Authorize.builder().url(url).build();
	}
}
