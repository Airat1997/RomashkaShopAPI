package org.example.controller;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
class RestApiController {
    private final ProductRepository productRepository;

    @Autowired
    public RestApiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    ResponseEntity<Iterable<Product>> getProducts(@RequestParam(required = false) String name, Double price, String priceCondition, Boolean productAvailability) {
        if (name != null && price == null && productAvailability == null) {
            return new ResponseEntity<>(productRepository.findByNameContaining(name), HttpStatus.OK);
        } else if (price != null && productAvailability == null && name == null) {
            if ("less".equalsIgnoreCase(priceCondition)) {
                return new ResponseEntity<>(productRepository.findByPriceLessThan(price), HttpStatus.OK);
            } else if ("greater".equalsIgnoreCase(priceCondition)) {
                return new ResponseEntity<>(productRepository.findByPriceGreaterThan(price), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(productRepository.findByPrice(price), HttpStatus.OK);
            }
        } else if (productAvailability != null && price == null && name == null) {
            return new ResponseEntity<>(productRepository.findByProductAvailability(productAvailability), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    ResponseEntity<Product> getProductsById(@PathVariable("id") Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Product> postProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<Product> putProduct(@PathVariable UUID id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } else {
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    void deleteProduct(@PathVariable UUID id) {
        productRepository.deleteById(id);
    }


}
