package com.henrique.hubspot.contact.adapter.repository.rest.dto;

import com.henrique.hubspot.contact.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRestRequestDto {

	Map<String, ?> properties;

	public ContactRestRequestDto(Contact domain) {
		properties = domain.getProperties();
	}

}
