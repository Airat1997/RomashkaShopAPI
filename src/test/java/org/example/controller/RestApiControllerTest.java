package org.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.example.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/non_correct_path"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProductsById() throws Exception {
        String uuidString = "0bc1978c-a219-4ccc-adfc-3157910002df";
        UUID uuid = UUID.fromString(uuidString);
        Product product = new Product("Test", "Test Product", 31.0, true, uuid);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + product.getName() + "\",\"description\":\""
                                + product.getDescription() + "\",\"price\":" + product.getPrice()
                                + ",\"productAvailability\":" + product.isProductAvailability()
                                + ",\"id\":\"" + product.getId() + "\"}"))
                        .andExpect(status().isOk());

                mockMvc.perform(get("/product/" + uuid))
                        .andExpect(status().isOk());
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