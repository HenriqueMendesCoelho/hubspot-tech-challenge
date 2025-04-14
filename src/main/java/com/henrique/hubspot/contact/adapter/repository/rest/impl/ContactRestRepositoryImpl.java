package com.henrique.hubspot.contact.adapter.repository.rest.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henrique.hubspot.contact.adapter.repository.rest.ContactRestRepository;
import com.henrique.hubspot.contact.adapter.repository.rest.dto.ContactRestRequestDto;
import com.henrique.hubspot.contact.adapter.repository.rest.dto.ContactRestResponseDto;
import com.henrique.hubspot.contact.domain.Contact;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRestRepositoryImpl implements ContactRestRepository {

	private final WebClient webClientHubspot;
	private final ObjectMapper mapper;

	@Override
	public Contact createContact(Contact contact) throws HubSpotCreateContactException {

		try {
			ContactRestResponseDto response = webClientHubspot.post()
					.uri("/oauth/v1/token")
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
}
