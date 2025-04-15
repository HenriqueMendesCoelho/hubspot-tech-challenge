package com.henrique.hubspot.webhook.adapter.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.henrique.hubspot.webhook.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class EventRequestDto {

	private Long eventId;
	private Integer subscriptionId;
	private Long portalId;
	private Long appId;
	private Long occurredAt;
	private String subscriptionType;
	private Integer attemptNumber;
	private Long objectId;
	private String changeFlag;
	private String changeSource;
	private String sourceId;

	public Event toDomain() {
		return Event.builder()
				.eventId(eventId)
				.subscriptionId(subscriptionId)
				.portalId(portalId)
				.appId(appId)
				.occurredAt(occurredAt)
				.subscriptionType(subscriptionType)
				.attemptNumber(attemptNumber)
				.objectId(objectId)
				.changeFlag(changeFlag)
				.changeSource(changeSource)
				.sourceId(sourceId)
				.build();
	}
}
