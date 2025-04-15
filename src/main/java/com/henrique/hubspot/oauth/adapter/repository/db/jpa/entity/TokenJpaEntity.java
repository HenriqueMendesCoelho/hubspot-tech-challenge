package com.henrique.hubspot.oauth.adapter.repository.db.jpa.entity;

import com.henrique.hubspot.oauth.domain.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenJpaEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String token;

	@Column
	private String refreshToken;

	@Column
	private Long expiresIn;

	@CreationTimestamp
	@Column
	private Timestamp createdAt;

	public TokenJpaEntity(Token token) {
		this.token = token.getAccessToken();
		this.refreshToken = token.getRefreshToken();
		this.expiresIn = token.getExpiresIn();
		this.createdAt = token.getCreatedAt();
	}

	public Token toDomain() {
		return Token.builder()
				.id(id)
				.accessToken(token)
				.refreshToken(refreshToken)
				.expiresIn(expiresIn)
				.createdAt(createdAt)
				.build();
	}
}
