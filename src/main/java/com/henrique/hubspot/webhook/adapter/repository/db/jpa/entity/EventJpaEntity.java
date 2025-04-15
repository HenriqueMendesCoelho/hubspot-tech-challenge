package com.henrique.hubspot.webhook.adapter.repository.db.jpa.entity;

import com.henrique.hubspot.webhook.domain.Event;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "webhook_events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventJpaEntity {

	@Id
	private Long eventId;

	@Column
	private Integer subscriptionId;

	@Column
	private Long portalId;

	@Column
	private Long appId;

	@Column
	private Long occurredAt;

	@Column
	private String subscriptionType;

	@Column
	private Integer attemptNumber;

	@Column
	private Long objectId;

	@Column
	private String changeFlag;

	@Column
	private String changeSource;

	@Column
	private String sourceId;

	public EventJpaEntity(Event domain) {
		eventId = domain.getEventId();
		subscriptionId = domain.getSubscriptionId();
		portalId = domain.getPortalId();
		appId = domain.getAppId();
		occurredAt = domain.getOccurredAt();
		subscriptionType = domain.getSubscriptionType();
		attemptNumber = domain.getAttemptNumber();
		objectId = domain.getObjectId();
		changeFlag = domain.getChangeFlag();
		changeSource = domain.getChangeSource();
		sourceId = domain.getSourceId();
	}

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
