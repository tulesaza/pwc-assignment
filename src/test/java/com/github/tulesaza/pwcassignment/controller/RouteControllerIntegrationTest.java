package com.github.tulesaza.pwcassignment.controller;

import com.github.tulesaza.pwcassignment.AbstractIntegrationTest;
import com.github.tulesaza.pwcassignment.model.RouteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;

class RouteControllerIntegrationTest extends AbstractIntegrationTest {

    @BeforeEach
    void setUp() throws IOException {
        var json = new ClassPathResource("countries.json").getContentAsByteArray();
        mockServer
                .when(request().withMethod("GET").withPath("/mledoze/countries/master/countries.json"))
                .respond(HttpResponse.response().withBody(json).withHeader("Content-Type", "text/plain; charset=utf-8"));
    }

    @Test
    void calculateRoute() {
        var response = restTemplate.getForEntity("/routing/CZE/ITA", RouteDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of("CZE", "AUT", "ITA"), Objects.requireNonNull(response.getBody()).getRoute());
    }

    @Test
    void noRoute() {
        var response = restTemplate.getForEntity("/routing/CZE/USA", RouteDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
}