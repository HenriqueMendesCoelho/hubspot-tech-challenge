package com.henrique.hubspot.contact.usecase.impl;

import com.henrique.hubspot.contact.adapter.repository.rest.ContactRestRepository;
import com.henrique.hubspot.contact.domain.Contact;
import com.henrique.hubspot.contact.usecase.CreateContactUseCase;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateContactUseCaseImpl implements CreateContactUseCase {

	private final ContactRestRepository repository;

	@Override
	public Contact execute(Contact contact) throws HubSpotCreateContactException {
		return repository.createContact(contact);
	}
}
