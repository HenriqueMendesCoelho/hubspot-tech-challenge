package com.henrique.hubspot.webhook.application.spring.controller;

import com.henrique.hubspot.webhook.adapter.controller.WebhookController;
import com.henrique.hubspot.webhook.adapter.controller.dto.EventRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hubspot/webhook")
@RequiredArgsConstructor
@Slf4j
public class WebhookSpringController {

	private final WebhookController controller;

	@PostMapping
	public ResponseEntity webhook(@RequestBody List<EventRequestDto> request) {
		try {
			controller.handle(request);
		} catch (Exception e) {
			log.error("Error processing webhook event:", e);
		}

		return ResponseEntity.ok().build();
	}

}
