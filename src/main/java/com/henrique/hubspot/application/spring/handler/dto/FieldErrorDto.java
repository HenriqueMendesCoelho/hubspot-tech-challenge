package com.henrique.hubspot.application.spring.handler.dto;

public record FieldErrorDto(
		String field,
		String message
) {
}
