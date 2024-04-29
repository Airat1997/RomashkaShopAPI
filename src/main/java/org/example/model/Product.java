package org.example.model;

import java.util.UUID;

public class Product {
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
