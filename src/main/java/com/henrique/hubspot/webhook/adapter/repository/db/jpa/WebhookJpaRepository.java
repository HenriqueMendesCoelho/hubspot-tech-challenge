package com.henrique.hubspot.webhook.adapter.repository.db.jpa;

import com.henrique.hubspot.webhook.adapter.repository.db.jpa.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebhookJpaRepository extends JpaRepository<EventJpaEntity, Long> {
}
