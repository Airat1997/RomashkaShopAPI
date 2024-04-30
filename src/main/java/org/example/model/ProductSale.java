package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "product_sale")
public class ProductSale {
    private UUID id;
    private String name;
    private Product product;
    private Integer quantity;
}
