package com.henrique.hubspot.webhook.usecase.impl;

import com.henrique.hubspot.webhook.adapter.repository.db.WebhookDBRepository;
import com.henrique.hubspot.webhook.domain.Event;
import com.henrique.hubspot.webhook.usecase.WebhookHandleUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebhookHandleUseCaseImpl implements WebhookHandleUseCase {
	private final WebhookDBRepository repository;

	@Override
	public void handle(List<Event> payload) {
		payload.forEach(e -> System.out.println(e.getSubscriptionType()));
		List<Event> contactsEvents = payload.stream()
				.filter(e -> "contact.creation".equals(e.getSubscriptionType()))
				.toList();

		repository.saveAll(contactsEvents);
		log.info("all contact.creation events processed and stored");
	}
}
