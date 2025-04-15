package com.henrique.hubspot.contact.adapter.controller;

import com.henrique.hubspot.contact.adapter.controller.dto.ContactRequestDto;
import com.henrique.hubspot.contact.adapter.controller.dto.ContactResponseDto;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;

public interface ContactController {

	ContactResponseDto create(ContactRequestDto request) throws HubSpotCreateContactException;

}
