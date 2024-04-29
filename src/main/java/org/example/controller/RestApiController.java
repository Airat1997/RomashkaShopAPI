package org.example.controller;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
class RestApiController {
    private final ProductRepository productRepository;

    @Autowired
    public RestApiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    Product getProductsById(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping("/product")
    Product postProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/product/{id}")
    ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } else {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

//    @DeleteMapping("/product/{id}")
//    void deleteProduct(@PathVariable UUID id) {
//        products.removeIf(c -> c.getId().equals(id));
//    }


}
