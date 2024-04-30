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
    Iterable<Product> getProducts(@RequestParam(required = false) String name, Double price, String priceCondition, Boolean productAvailability) {
        if (name != null && price == null && productAvailability == null) {
            return productRepository.findByNameContaining(name);
        } else if (price != null && productAvailability == null && name == null) {
            if ("less".equalsIgnoreCase(priceCondition)){
                return productRepository.findByPriceLessThan(price);
            } else if ("greater".equalsIgnoreCase(priceCondition)){
                return productRepository.findByPriceGreaterThan(price);
            } else {
                return productRepository.findByPrice(price);
            }
        } else if (productAvailability != null && price == null && name == null) {
            return productRepository.findByProductAvailability(productAvailability);
        } else {
            return productRepository.findAll();
        }
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

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable UUID id) {
        productRepository.deleteById(id);
    }


}
