package org.example.controller;

import org.example.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
class RestApiController {
    private ArrayList<Product> products = new ArrayList<>();

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
    Product postProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    @PutMapping("/product/{id}")
    Product putProduct(@PathVariable UUID id, @RequestBody Product product) {
        int productIndex = -1;
        for (Product c : products) {
            if (c.getId().equals(id)) {
                productIndex = products.indexOf(c);
                products.set(productIndex, product);
            }
        }
        return (productIndex == -1) ? postProduct(product) : product;
    }

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable UUID id) {
        products.removeIf(c -> c.getId().equals(id));
    }


}
