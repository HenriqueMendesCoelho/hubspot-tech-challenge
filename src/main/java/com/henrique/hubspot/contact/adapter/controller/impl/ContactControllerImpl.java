package com.henrique.hubspot.contact.adapter.controller.impl;

import com.henrique.hubspot.contact.adapter.controller.ContactController;
import com.henrique.hubspot.contact.adapter.controller.dto.ContactRequestDto;
import com.henrique.hubspot.contact.adapter.controller.dto.ContactResponseDto;
import com.henrique.hubspot.contact.usecase.CreateContactUseCase;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ContactControllerImpl implements ContactController {

	private final CreateContactUseCase createContactUseCase;

	@Override
	public ContactResponseDto create(ContactRequestDto request) throws HubSpotCreateContactException {
		return new ContactResponseDto(createContactUseCase.execute(request.toDomain()));
	}
}
