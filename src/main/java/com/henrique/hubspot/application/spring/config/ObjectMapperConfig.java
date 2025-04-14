package com.henrique.hubspot.application.spring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class ObjectMapperConfig {

	@Bean
	@Primary
	ObjectMapper objectMapper() {
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
				.registerModule(new JavaTimeModule())
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
				.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	@Bean
	Jackson2JsonDecoder jackson2JsonDecoder() {
		return new Jackson2JsonDecoder(objectMapper());
	}
}
