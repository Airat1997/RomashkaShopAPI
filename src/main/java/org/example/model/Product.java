package org.example.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 256)
    private String name;
    @Column(name = "description", length = 4096)
    private String description;
    @Column(name = "price")
    private double price = 0;
    @Column(name = "product_availability")
    private boolean productAvailability;

    public Product() {};

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
