package com.coffee.spring.mastering.integration.controller;

import com.coffee.spring.mastering.domain.Ranch;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RanchesControllerTest {

    @LocalServerPort
    private int port;

    private static final String LOCAL_HOST = "http://localhost:";

    private final String RANCHES_PATH = "/ranches";
    private final String RANCHES_WITH_USER_PATH = "/ranches/name/Ricardo";
    private final String RANCHES_BY_ID_PATH = "/ranches/1";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void retrieveRanchesControllerTest() throws Exception {

        String expected = "[{\"id\":1,\"name\": \"Ricardo\",\"city\": \"Pittsburgh\",\"active\": true},{\"id\": 2,\"name\": \"Mullika\",\"city\": \"Bangkok\",\"active\": true},{\"id\": 3,\"name\": \"Teresa\",\"city\": \"Pachuca\",\"active\": true}]";
        ResponseEntity<String> response = restTemplate.getForEntity(
                                                        createURL(RANCHES_PATH),
                                                        String.class);
        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    public void getRanchByUser() throws Exception {

        String expected = "{\"id\": 1,\"name\": \"Ricardo\",\"city\": \"Pittsburgh\",\"active\": true}";
        ResponseEntity<String> response = restTemplate.getForEntity(createURL(RANCHES_WITH_USER_PATH),
                String.class);
        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    public void getRanchById() throws Exception {

        String expected = "{\"id\": 1,\"name\": \"Ricardo\",\"city\": \"Pittsburgh\",\"active\": true}";
        ResponseEntity<String> response = restTemplate.getForEntity(createURL(RANCHES_BY_ID_PATH), String.class);
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void createRanch() throws Exception {

        Ranch requestRanch = Ranch.builder().name("Alberth").city("Apulco").build();
        URI location = restTemplate.postForLocation(createURL(RANCHES_PATH),
                                                    requestRanch);
        MatcherAssert.assertThat(location.getPath(), CoreMatchers.containsString("/ranches/4"));

    }

    private String createURL(String path) {
        return LOCAL_HOST + port + path;
    }
}
