package com.henrique.hubspot.webhook.adapter.controller.impl;

import com.henrique.hubspot.webhook.adapter.controller.WebhookController;
import com.henrique.hubspot.webhook.adapter.controller.dto.EventRequestDto;
import com.henrique.hubspot.webhook.domain.Event;
import com.henrique.hubspot.webhook.usecase.WebhookHandleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebhookControllerImpl implements WebhookController {

	private final WebhookHandleUseCase webhookHandleUseCase;

	@Override
	public void handle(List<EventRequestDto> payload) {
		payload.forEach(e -> System.out.println(e.getSubscriptionType()));
		List<Event> events = payload.stream().map(EventRequestDto::toDomain).toList();
		webhookHandleUseCase.handle(events);
	}
}
