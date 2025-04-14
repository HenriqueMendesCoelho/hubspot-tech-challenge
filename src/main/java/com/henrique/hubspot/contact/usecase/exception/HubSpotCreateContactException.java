package com.henrique.hubspot.contact.usecase.exception;

public class HubSpotCreateContactException extends Exception {
	public HubSpotCreateContactException() {
		super("Error creating new contact in hubspot");
	}
}
