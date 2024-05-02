package org.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
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
    String uuidString = "0bc1978c-a219-4ccc-adfc-3157910002df";
    UUID uuid = UUID.fromString(uuidString);
    String json = """
            {
                "description": "Test description",
                "id": "0bc1978c-a219-4ccc-adfc-3157910002df",
                "name": "Test",
                "price": 10000.0,
                "productAvailability": true
            }
            """;
    String newJson = """
            {
                "description": "New Test description",
                "id": "0bc1978c-a219-4ccc-adfc-3157910002df",
                "name": "New Test",
                "price": 250.0,
                "productAvailability": false
            }
            """;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RestApiController()).build();
    }
    @Test
    void getProducts() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/non_correct_path"))
                .andExpect(status().isNotFound());
    }

    @Test
    void postProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void getProductsById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/product/" + uuid))
                .andExpect(status().isOk());
    }


    @Test
    void putProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders.put("/product/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct() {
    }
}