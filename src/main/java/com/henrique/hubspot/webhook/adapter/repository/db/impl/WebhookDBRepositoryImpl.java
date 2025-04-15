package com.henrique.hubspot.webhook.adapter.repository.db.impl;

import com.henrique.hubspot.webhook.adapter.repository.db.WebhookDBRepository;
import com.henrique.hubspot.webhook.adapter.repository.db.jpa.WebhookJpaRepository;
import com.henrique.hubspot.webhook.adapter.repository.db.jpa.entity.EventJpaEntity;
import com.henrique.hubspot.webhook.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebhookDBRepositoryImpl implements WebhookDBRepository {

	private final WebhookJpaRepository repository;

	@Override
	public List<Event> saveAll(List<Event> events) {
		List<EventJpaEntity> newEvents = events.stream().map(EventJpaEntity::new).toList();

		return repository.saveAll(newEvents).stream().map(EventJpaEntity::toDomain).toList();
	}
}
