package com.henrique.hubspot.application.spring.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientConfig {

	private final Jackson2JsonDecoder decoder;

	@Value("${hubspot.api.url}")
	private String hubspotUrl;

	@Bean
	WebClient webClientHubspot() {
		return WebClient.builder()
				.baseUrl(hubspotUrl)
				.filter(rateLimitHandler())
				.codecs(clientCodecConfigurer -> clientCodecConfigurer.customCodecs().register(decoder))
				.build();
	}

	private ExchangeFilterFunction rateLimitHandler() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			var headers = clientResponse.headers().asHttpHeaders();

			String remaining = headers.getFirst("X-HubSpot-RateLimit-Remaining");
			String interval = headers.getFirst("X-HubSpot-RateLimit-Interval-Milliseconds");

			if ("0".equals(remaining)) {
				long waitTime = Long.parseLong(interval != null ? interval : "1000");
				log.warn("Rate limited! Waiting {}ms", waitTime);
				return Mono.delay(Duration.ofMillis(waitTime)).then(Mono.just(clientResponse));
			}

			return Mono.just(clientResponse);
		});
	}

}
