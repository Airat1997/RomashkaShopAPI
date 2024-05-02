package org.example.model;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class Document {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name", length = 255)
    private String name;
    @ManyToOne
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}