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
    ResponseEntity<Iterable<Product>> getProducts(@RequestParam(required = false) String name,
            Double price, String priceCondition, Boolean productAvailability) {
        Iterable<Product> products;
        if (name != null && price == null && productAvailability == null) {
            products = findProductsByName(name);
        } else if (price != null && productAvailability == null && name == null) {
            products = findByPrice(price, priceCondition);
        } else if (productAvailability != null && price == null && name == null) {
            products = findByProductAvailability(productAvailability);
        } else {
            products = productRepository.findAll();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    Iterable<Product> findProductsByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    Iterable<Product> findByPrice(Double price, String priceCondition) {
        if ("less".equalsIgnoreCase(priceCondition)) {
            return productRepository.findByPriceLessThan(price);
        } else if ("greater".equalsIgnoreCase(priceCondition)) {
            return productRepository.findByPriceGreaterThan(price);
        } else {
            return productRepository.findByPrice(price);
        }
    }

    Iterable<Product> findByProductAvailability(Boolean productAvailability) {
        return productRepository.findByProductAvailability(productAvailability);
    }

    @GetMapping("/sortedByPriceAsc")
    Iterable<Product> findAllSortedByPriceAsc() {
        return productRepository.findAllSortedByPriceAsc();
    }

    @GetMapping("/sortedByPriceDesc")
    Iterable<Product> findAllSortedByPriceDesc() {
        return productRepository.findAllSortedByPriceDesc();
    }

    @GetMapping("/sortedByNameAsc")
    Iterable<Product> findAllSortedByNameAsc() {
        return productRepository.findAllSortedByNameAsc();
    }

    @GetMapping("/sortedByNameDesc")
    Iterable<Product> findAllSortedByNameDesc() {
        return productRepository.findAllSortedByNameDesc();
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
