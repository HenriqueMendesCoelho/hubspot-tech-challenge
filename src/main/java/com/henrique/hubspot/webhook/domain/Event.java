package com.henrique.hubspot.webhook.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
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
}
