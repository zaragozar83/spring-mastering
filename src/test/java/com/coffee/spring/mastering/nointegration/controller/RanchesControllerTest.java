package com.coffee.spring.mastering.nointegration.controller;

import com.coffee.spring.mastering.controller.RanchController;
import com.coffee.spring.mastering.domain.Ranch;
import com.coffee.spring.mastering.service.RanchService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RanchController.class)
public class RanchesControllerTest {

    private final String RANCHES_PATH = "/ranches";
    private final String RANCHES_WITH_USER_PATH = "/ranches/Ricardo";

    private final String USER = "Ricardo";

    @Autowired
    MockMvc mvc;

    @MockBean
    RanchService ranchService;

    @Test
    public void retrieveRanchesTest() throws Exception {

        List<Ranch> ranches = initRanchesList();

        Mockito.when(ranchService.retrieveRanches(ArgumentMatchers.anyString())).thenReturn(ranches);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(RANCHES_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":1,\"name\": \"Ricardo\",\"city\": \"Pittsburgh\",\"active\": true},{\"id\": 2,\"name\": \"Mullika\",\"city\": \"Bangkok\",\"active\": true},{\"id\": 3,\"name\": \"Teresa\",\"city\": \"Pachuca\",\"active\": true}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void getRanchByUserTest() throws Exception {

        Ranch ranch = ranchByUser(USER);

        Mockito.when(ranchService.getRanchByUser(ArgumentMatchers.anyString())).thenReturn(ranch);

        String expected = "{\"id\": 1,\"name\": \"Ricardo\",\"city\": \"Pittsburgh\",\"active\": true}";

        MvcResult result = mvc.perform(
          MockMvcRequestBuilders.get(RANCHES_WITH_USER_PATH)
          .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    private Ranch ranchByUser(String name) {
        List<Ranch> ranches = initRanchesList();

        return ranches.stream()
                .filter(ranch -> ranch.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private List<Ranch> initRanchesList() {
        return Arrays.asList(
                Ranch.builder()
                        .id(1)
                        .active(true)
                        .name("Ricardo")
                        .city("Pittsburgh")
                        .build(),
                Ranch.builder()
                        .id(2)
                        .active(true)
                        .name("Mullika")
                        .city("Bangkok")
                        .build(),
                Ranch.builder()
                        .id(3)
                        .active(true)
                        .name("Teresa")
                        .city("Pachuca")
                        .build()
        );
    }
}
