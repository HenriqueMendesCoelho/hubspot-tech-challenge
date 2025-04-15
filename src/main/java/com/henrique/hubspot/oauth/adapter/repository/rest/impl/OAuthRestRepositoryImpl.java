package com.henrique.hubspot.oauth.adapter.repository.rest.impl;

import com.henrique.hubspot.oauth.adapter.repository.rest.OAuthRestRepositoty;
import com.henrique.hubspot.oauth.adapter.repository.rest.dto.TokenRestResponseDto;
import com.henrique.hubspot.oauth.domain.Token;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OAuthRestRepositoryImpl implements OAuthRestRepositoty {

	private static final String REDIRECT_URI = "http://localhost:8080/api/hubspot/callback";

	private final WebClient webClientHubspot;

	@Value("${hubspot.clientid}")
	private String clientId;

	@Value("${hubspot.clientsecret}")
	private String clientSecret;

	@Override
	public Token exchangeCode(String code) throws HubspotOAuthException {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "authorization_code");
		formData.add("client_id", clientId);
		formData.add("client_secret", clientSecret);
		formData.add("redirect_uri", REDIRECT_URI);
		formData.add("code", code);
		
		try {
			TokenRestResponseDto response = webClientHubspot.post()
					.uri("/oauth/v1/token")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.body(BodyInserters.fromFormData(formData))
					.retrieve()
					.bodyToMono(TokenRestResponseDto.class)
					.retryWhen(Retry.backoff(3, Duration.ofSeconds(1)).jitter(0.75))
					.block();

			return response.toDomain();
		} catch (Exception e) {
			log.error("Error on code exchange with hubspot raised: ", e);
			throw new HubspotOAuthException();
		}
	}

	@Override
	public Token refreshToken(String refreshToken) throws HubspotOAuthException {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "refresh_token");
		formData.add("refresh_token", refreshToken);
		formData.add("client_id", clientId);
		formData.add("client_secret", clientSecret);

		try {
			TokenRestResponseDto response = webClientHubspot.post()
					.uri("/oauth/v1/token")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.body(BodyInserters.fromFormData(formData))
					.retrieve()
					.bodyToMono(TokenRestResponseDto.class)
					.retryWhen(Retry.backoff(3, Duration.ofSeconds(1)).jitter(0.75))
					.block();

			return response.toDomain();
		} catch (Exception e) {
			log.error("Error on code exchange with hubspot raised: ", e);
			throw new HubspotOAuthException();
		}
	}
}
