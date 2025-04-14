package com.henrique.hubspot.application.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

	private final Jackson2JsonDecoder decoder;

	@Value("${hubspot.api.url}")
	private String hubspotUrl;

	@Bean
	WebClient webClientHubspot() {
		return WebClient.builder()
				.baseUrl(hubspotUrl)
				.codecs(clientCodecConfigurer -> clientCodecConfigurer.customCodecs().register(decoder))
				.build();
	}

}
