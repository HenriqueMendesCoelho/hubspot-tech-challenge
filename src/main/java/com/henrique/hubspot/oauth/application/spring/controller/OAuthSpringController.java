package com.henrique.hubspot.oauth.application.spring.controller;

import com.henrique.hubspot.oauth.adapter.controller.OAuthController;
import com.henrique.hubspot.oauth.adapter.controller.dto.AuthorizeResponseDto;
import com.henrique.hubspot.oauth.adapter.controller.dto.CallbackResponseDto;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hubspot")
@RequiredArgsConstructor
public class OAuthSpringController {

	private final OAuthController controller;

	@GetMapping("/authorize")
	public ResponseEntity<AuthorizeResponseDto> authorize() {
		AuthorizeResponseDto dto = controller.authorize();

		return ResponseEntity.ok(dto);
	}

	@GetMapping("/callback")
	public ResponseEntity<CallbackResponseDto> callback(@RequestParam(required = true) String code)
			throws HubspotOAuthException {
		CallbackResponseDto dto = controller.callback(code);

		return ResponseEntity.ok(dto);
	}
}
