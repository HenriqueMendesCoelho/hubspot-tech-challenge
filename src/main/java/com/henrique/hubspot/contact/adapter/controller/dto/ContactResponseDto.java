package com.henrique.hubspot.contact.adapter.controller.dto;

import com.henrique.hubspot.contact.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDto {
	String id;
	Map<String, ?> properties;

	public ContactResponseDto(Contact domain) {
		properties = domain.getProperties();
	}
}
