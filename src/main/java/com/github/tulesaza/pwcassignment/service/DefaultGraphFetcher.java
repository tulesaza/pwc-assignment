package com.github.tulesaza.pwcassignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tulesaza.pwcassignment.model.CountryDTO;
import com.github.tulesaza.pwcassignment.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: should be cached
// TODO user open api instead
// TODO: write unit tests
@Service
public class DefaultGraphFetcher implements GraphFetcher {
    private final ApplicationProperties properties;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public DefaultGraphFetcher(ApplicationProperties properties, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.properties = properties;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    // TODO: handle API errors properly
    @Override
    public Map<String, List<String>> graph() {
        final String response = Objects.requireNonNull(restTemplate.exchange(properties.getCountriesUrl(),
                HttpMethod.GET,
                null,
                String.class
        ).getBody());
        try {
            return Arrays.stream(objectMapper.readValue(response, CountryDTO[].class))
                    .collect(Collectors.toMap(CountryDTO::getCca3, CountryDTO::getBorders));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // TODO handle exception properly
            throw new RuntimeException("Error while parsing JSON response");
        }

    }
}
