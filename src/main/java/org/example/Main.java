package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@RestController
class RestApiController {
    private List<Product> products = new ArrayList<>();

    public RestApiController() {
        products.addAll(List.of(new Product("Game", "rpg", 2, true, UUID.randomUUID()), new Product("book", "C++", 3, true, UUID.randomUUID())));
    }

    @GetMapping("/products")
    Iterable<Product> getProducts() {
        return products;
    }

    @GetMapping("/{id}")
    Optional<Product> getProductsById(@PathVariable UUID id) {
        for (Product c : products) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/product")
    Product postProduct(@RequestBody Product product){
        products.add(product);
        return product;
    }



}

class Product {
    private String name;
    private String description;
    private double price = 0;
    private boolean productAvailability;
    private UUID id;

    public Product(String name, String description, double price, boolean productAvailability, UUID id) {
        if (name.length() > 256) throw new IllegalArgumentException();
        if (price < 0) throw new IllegalArgumentException();
        if (description.length() > 4096) throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.price = price;
        this.productAvailability = productAvailability;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(boolean productAvailability) {
        this.productAvailability = productAvailability;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
