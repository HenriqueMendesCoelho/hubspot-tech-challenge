package com.henrique.hubspot.webhook.adapter.controller;

import com.henrique.hubspot.webhook.adapter.controller.dto.EventRequestDto;

import java.util.List;

public interface WebhookController {

	void handle(List<EventRequestDto> payload);

}
