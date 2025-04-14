package com.henrique.hubspot.oauth.usecase.exception;

public class HubspotOAuthException extends Exception {
	public HubspotOAuthException() {
		super("Failed to exchange code for token");
	}
}
