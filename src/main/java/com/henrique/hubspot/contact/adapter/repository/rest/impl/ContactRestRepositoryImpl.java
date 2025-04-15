package com.henrique.hubspot.contact.adapter.repository.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.hubspot.contact.adapter.repository.rest.ContactRestRepository;
import com.henrique.hubspot.contact.adapter.repository.rest.dto.ContactRestRequestDto;
import com.henrique.hubspot.contact.adapter.repository.rest.dto.ContactRestResponseDto;
import com.henrique.hubspot.contact.domain.Contact;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;
import com.henrique.hubspot.oauth.adapter.repository.db.TokenDBRepository;
import com.henrique.hubspot.oauth.domain.Token;
import com.henrique.hubspot.oauth.usecase.OAuthRefreshTokenUseCase;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRestRepositoryImpl implements ContactRestRepository {

	private final WebClient webClientHubspot;
	private final ObjectMapper mapper;
	private final TokenDBRepository tokenDBRepository;
	private final OAuthRefreshTokenUseCase oAuthRefreshTokenUseCase;

	@Override
	public Contact createContact(Contact contact) throws HubSpotCreateContactException {
		try {
			String token = getToken();

			if (token == null)
				return null;

			ContactRestResponseDto response = webClientHubspot.post()
					.uri("/crm/v3/objects/contacts")
					.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(mapper.writeValueAsString(new ContactRestRequestDto(contact)))
					.retrieve()
					.bodyToMono(ContactRestResponseDto.class)
					.retryWhen(Retry.backoff(3, Duration.ofSeconds(2)).jitter(0.75))
					.block();

			return response.toDomain();
		} catch (Exception e) {
			log.error("Error on code exchange with hubspot raised: ", e);
			throw new HubSpotCreateContactException();
		}
	}

	private String getToken() throws HubspotOAuthException {
		List<Token> tokens = tokenDBRepository.findAll();
		if (CollectionUtils.isEmpty(tokens)) {
			return null;
		}

		Token token = tokens.get(0);

		Long currentTime = System.currentTimeMillis();
		Long expireTime = token.getCreatedAt().getTime() + (token.getExpiresIn() * 1000);
		if (currentTime > expireTime) {
			token = oAuthRefreshTokenUseCase.execute(token.getRefreshToken());
		}

		return token.getAccessToken();
	}
}
