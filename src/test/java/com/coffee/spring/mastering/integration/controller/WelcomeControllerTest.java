package com.coffee.spring.mastering.integration.controller;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WelcomeControllerTest {

    private static final String LOCAL_HOST = "http://localhost:";
    private final String WELCOME_PATH = "/welcome";
    private final String WELCOME_WITH_OBJECT_PATH = "/welcome-with-object";
    private final String WELCOME_PATH_VARIABLE = "/welcome/name/Ricardo";

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    @Test
    public void welcomeController () throws Exception{

        String expectedResponse = "Hello World";

        ResponseEntity<String> response = template.getForEntity(createURL(WELCOME_PATH),
                                                                String.class);

        MatcherAssert.assertThat(response.getBody(), CoreMatchers.equalTo(expectedResponse));

    }

    @Test
    public void welcomeWithObjectController () throws Exception {
        String expectedResponse = "Hello World";

        ResponseEntity<String> response = template.getForEntity(createURL(WELCOME_WITH_OBJECT_PATH),
                                                                String.class);

        MatcherAssert.assertThat(response.getBody(), CoreMatchers.containsString(expectedResponse));
    }

    @Test
    public void welcomePathVariable () throws Exception {

        String expectedResponse = "Hello World, Ricardo!";

        ResponseEntity<String> response = template.getForEntity(createURL(WELCOME_PATH_VARIABLE),
                                                                String.class);
        MatcherAssert.assertThat(response.getBody(), CoreMatchers.containsString(expectedResponse));
    }


    private String createURL(String path) {
        return LOCAL_HOST + port + path;
    }
}
