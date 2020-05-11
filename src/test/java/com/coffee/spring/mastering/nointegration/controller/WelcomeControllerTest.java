package com.coffee.spring.mastering.nointegration.controller;

import com.coffee.spring.mastering.controller.WelcomneController;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WelcomneController.class)
public class WelcomeControllerTest {

    @Autowired
    MockMvc mvc;

    private final String WELCOME_PATH = "/welcome";
    private final String WELCOME_WITH_OBJECT_PATH = "/welcome-with-object";
    private final String WELCOME_PATH_VARIABLE = "/welcome/name/Ricardo";

    @Test
    public void welcomeController() throws Exception{

        String expectedResponse = "Hello World";

        mvc.perform(
                MockMvcRequestBuilders.get(WELCOME_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

    }

    @Test
    public void welcomeWithObject() throws Exception{

        String expectedResponse = "Hello World";

        mvc.perform(MockMvcRequestBuilders.get(WELCOME_WITH_OBJECT_PATH)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.containsString(expectedResponse)));

    }

    @Test
    public void welcomeWithPathVariable() throws Exception {

        String expectedResponse = "Hello World, Ricardo!";

        mvc.perform(
          MockMvcRequestBuilders.get(WELCOME_PATH_VARIABLE)
          .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.containsString(expectedResponse)));

    }
}
