package org.example.controller;

import org.example.model.Product;
import org.example.model.ProductDelivery;
import org.example.repository.ProductDeliveryRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class RestApiControllerProductDelivery {

    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;

    @Autowired
    RestApiControllerProductDelivery(ProductDeliveryRepository productDeliveryRepository,
            ProductRepository productRepository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/product_delivery")
    Iterable<ProductDelivery> getProductDelivery() {
        return productDeliveryRepository.findAll();
    }

    @GetMapping("/product_delivery/{id}")
    ProductDelivery getProductDeliveryById(@PathVariable("id") ProductDelivery productDelivery) {
        return productDelivery;
    }

    @PostMapping("/product_delivery")
    ProductDelivery postProductDelivery(@RequestBody ProductDelivery productDelivery) {
        if (productRepository.existsById(productDelivery.getProduct().getId())) {
            return productDeliveryRepository.save(productDelivery);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @PutMapping("/product_delivery/{id}")
    ResponseEntity<ProductDelivery> putProductDelivery(@PathVariable UUID id,
            @RequestBody ProductDelivery productDelivery) {
        if (!productDeliveryRepository.existsById(id)) {
            productDeliveryRepository.save(productDelivery);
            return new ResponseEntity<>(productDelivery, HttpStatus.CREATED);
        } else {
            productDeliveryRepository.save(productDelivery);
            return new ResponseEntity<>(productDelivery, HttpStatus.OK);
        }
    }

    @DeleteMapping("/product_delivery/{id}")
    void deleteProductDelivery(@PathVariable UUID id) {
        productDeliveryRepository.deleteById(id);
    }


}
