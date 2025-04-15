package com.henrique.hubspot.webhook.usecase;

import com.henrique.hubspot.webhook.domain.Event;

import java.util.List;

public interface WebhookHandleUseCase {

	void handle(List<Event> payload);

}
