package com.henrique.hubspot.oauth.adapter.controller;

import com.henrique.hubspot.oauth.adapter.controller.dto.AuthorizeResponseDto;
import com.henrique.hubspot.oauth.adapter.controller.dto.CallbackResponseDto;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;

public interface OAuthController {

	AuthorizeResponseDto authorize();

	CallbackResponseDto callback(String code) throws HubspotOAuthException;
}
