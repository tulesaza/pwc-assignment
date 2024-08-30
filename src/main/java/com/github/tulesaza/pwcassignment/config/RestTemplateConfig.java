package com.github.tulesaza.pwcassignment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    // TODO consider using circuit breaker pattern in case of slow API
    @Bean
    public RestTemplate restTemplate(ObjectMapper objectMapper) {
        return new RestTemplateBuilder()
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
