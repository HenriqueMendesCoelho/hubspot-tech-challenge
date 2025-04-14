package com.henrique.hubspot.application.spring.handler;

import com.henrique.hubspot.application.spring.handler.dto.FieldErrorDto;
import com.henrique.hubspot.contact.usecase.exception.HubSpotCreateContactException;
import com.henrique.hubspot.oauth.usecase.exception.HubspotOAuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class SpringControllerAdviceHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
		log.error("Request: {} raised:", request.getRequestURI(), ex);

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

		problemDetail.setTitle("Bad Request");
		problemDetail.setInstance(URI.create(request.getRequestURI()));
		problemDetail.setDetail("Validation failed for one or more fields.");

		List<FieldErrorDto> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> new FieldErrorDto(error.getField(), error.getDefaultMessage()))
				.toList();
		problemDetail.setProperty("errors", errors);

		return problemDetail;
	}

	@ExceptionHandler(HubSpotCreateContactException.class)
	public ProblemDetail handleHubspotOAuthException(HubspotOAuthException ex, HttpServletRequest request) {
		log.error("Request: {} raised: {}", request.getRequestURI(), ex.getMessage(), ex);

		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, ex.getMessage());

		problemDetail.setTitle("HubSpot Integration Error");
		problemDetail.setInstance(URI.create(request.getRequestURI()));
		problemDetail.setDetail("Failed create new contact with HubSpot.");
		problemDetail.setProperty("integration", "hubspot");

		return problemDetail;
	}
}
