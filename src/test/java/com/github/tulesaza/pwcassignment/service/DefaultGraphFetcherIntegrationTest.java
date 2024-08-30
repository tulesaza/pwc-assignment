package com.github.tulesaza.pwcassignment.service;

import com.github.tulesaza.pwcassignment.AbstractIntegrationTest;
import com.github.tulesaza.pwcassignment.properties.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockserver.model.HttpRequest.request;

class DefaultGraphFetcherIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private DefaultGraphFetcher sut;

    @BeforeEach
    void setUp() throws IOException {
        var json = new ClassPathResource("countries.json").getContentAsByteArray();
        mockServer
                .when(request().withMethod("GET").withPath("/mledoze/countries/master/countries.json"))
                .respond(HttpResponse.response().withBody(json).withHeader("Content-Type", "text/plain; charset=utf-8"));
    }

    @Test
    void basicPositiveTest() {
        var graph = sut.graph();
        assertFalse(graph.isEmpty());
        assertTrue(graph.containsKey("POL"));
    }
}