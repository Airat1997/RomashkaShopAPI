package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        products.addAll(List.of(new Product("Game", "rpg", 2, true), new Product("book", "C++", 3, true)));
    }

    @GetMapping("/products")
    Iterable<Product> getProducts() {
        return products;
    }

}

class Product {
    private String name;
    private String description;
    private double price = 0;
    private boolean productAvailability;

    public Product(String name, String description, double price, boolean productAvailability) {
        if (name.length() > 256) throw new IllegalArgumentException();
        if (price < 0) throw new IllegalArgumentException();
        if (description.length() > 4096) throw new IllegalArgumentException();
        this.name = name;
        this.description = description;
        this.price = price;
        this.productAvailability = productAvailability;
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
}
