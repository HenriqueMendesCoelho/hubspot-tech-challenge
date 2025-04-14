package com.henrique.hubspot.oauth.adapter.repository.db;

import com.henrique.hubspot.oauth.domain.Token;

import java.util.List;

public interface TokenDBRepository {

	List<Token> findAll();

	Token save(Token token);
}
