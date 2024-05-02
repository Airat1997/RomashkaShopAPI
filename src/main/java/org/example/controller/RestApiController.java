package org.example.controller;

import org.example.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
class RestApiController {

    private ArrayList<Product> products = new ArrayList<>();

    @GetMapping
    ResponseEntity<Iterable<Product>> getProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Product>> getProductsById(@PathVariable UUID id) {
        for (Product c : products) {
            if (c.getId().equals(id)) {
                return new ResponseEntity<>(Optional.of(c), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity<Product> postProduct(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        int productIndex = -1;
        for (Product c : products) {
            if (c.getId().equals(id)) {
                productIndex = products.indexOf(c);
                products.set(productIndex, product);
            }
        }
        return (productIndex == -1) ? postProduct(product)
                : new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    void deleteProduct(@PathVariable UUID id) {
        products.removeIf(c -> c.getId().equals(id));
    }


}
