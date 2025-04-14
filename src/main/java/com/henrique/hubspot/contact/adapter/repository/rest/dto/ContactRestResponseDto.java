package com.henrique.hubspot.contact.adapter.repository.rest.dto;

import com.henrique.hubspot.contact.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRestResponseDto {
	String id;
	Map<String, ?> properties;

	public Contact toDomain() {
		return Contact.builder().id(id).properties(properties).build();
	}
}
