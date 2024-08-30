package com.github.tulesaza.pwcassignment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockserver.cli.Main.Arguments.serverPort;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PwcAssignmentApplication.class
)
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;


    protected ClientAndServer mockServer;

    @BeforeAll
    void startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(48080);
    }

    @AfterAll
    void stopMockServer() {
        mockServer.stop();
    }

    @AfterEach
    void resetMockServer() {
        mockServer.reset();
    }

    @BeforeEach
    void setUp() {
        var builder = new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .rootUri("http://localhost:%d" .formatted(port));
        restTemplate = new TestRestTemplate(builder);
    }
}
