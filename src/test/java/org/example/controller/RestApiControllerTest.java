package org.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class RestApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RestApiController()).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/non_correct_path"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductsById() {
    }

    @Test
    void postProduct() {
    }

    @Test
    void putProduct() {
    }

    @Test
    void deleteProduct() {
    }
}