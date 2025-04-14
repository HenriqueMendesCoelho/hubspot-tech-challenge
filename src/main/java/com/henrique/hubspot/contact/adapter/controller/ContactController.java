package com.henrique.hubspot.contact.adapter.controller;

import com.henrique.hubspot.contact.adapter.controller.dto.ContactRequestDto;
import com.henrique.hubspot.contact.adapter.controller.dto.ContactResponseDto;

public interface ContactController {

	ContactResponseDto create(ContactRequestDto request);

}
