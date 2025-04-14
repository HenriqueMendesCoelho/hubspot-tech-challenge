package com.henrique.hubspot.oauth.adapter.repository.db.impl;

import com.henrique.hubspot.oauth.adapter.repository.db.TokenDBRepository;
import com.henrique.hubspot.oauth.adapter.repository.db.jpa.TokenJpaRepository;
import com.henrique.hubspot.oauth.adapter.repository.db.jpa.entity.TokenJpaEntity;
import com.henrique.hubspot.oauth.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TokenDBRepositoryImpl implements TokenDBRepository {

	private final TokenJpaRepository repository;

	@Override
	public List<Token> findAll() {
		return repository.findAll().stream().map(TokenJpaEntity::toDomain).toList();
	}

	@Override
	public Token save(Token token) {
		return repository.save(new TokenJpaEntity(token)).toDomain();
	}
}
