package com.henrique.hubspot.webhook.adapter.repository.db;

import com.henrique.hubspot.webhook.domain.Event;

import java.util.List;

public interface WebhookDBRepository {

	List<Event> saveAll(List<Event> events);

}
