package com.henrique.hubspot.contact.application.spring.controller;

import com.henrique.hubspot.contact.adapter.controller.ContactController;
import com.henrique.hubspot.contact.adapter.controller.dto.ContactRequestDto;
import com.henrique.hubspot.contact.adapter.controller.dto.ContactResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hubspot/contact")
@RequiredArgsConstructor
public class ContactSpringController {

	private final ContactController controller;

	@PostMapping
	public ResponseEntity<ContactResponseDto> createContact(@RequestBody @Valid ContactRequestDto request) {
		ContactResponseDto response = controller.create(request);

		return ResponseEntity.ok(response);
	}

}
