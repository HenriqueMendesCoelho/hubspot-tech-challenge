package com.henrique.hubspot.oauth.adapter.repository.db.jpa;

import com.henrique.hubspot.oauth.adapter.repository.db.jpa.entity.TokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenJpaEntity, Long> {
}

