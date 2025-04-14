package com.henrique.hubspot.contact.usecase;

import com.henrique.hubspot.contact.domain.Contact;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;

public interface CreateContactUseCase {
	Contact execute(Contact contact) throws HubSpotCreateContactException;
}
