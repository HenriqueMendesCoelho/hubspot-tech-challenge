package com.henrique.hubspot.contact.adapter.repository.rest;

import com.henrique.hubspot.contact.domain.Contact;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;

public interface ContactRestRepository {
	Contact createContact(Contact contact) throws HubSpotCreateContactException;
}
