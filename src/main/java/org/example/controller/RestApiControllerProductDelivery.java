package org.example.controller;

import org.example.model.ProductDelivery;
import org.example.repository.ProductDeliveryRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiControllerProductDelivery {
    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;

    @Autowired
    RestApiControllerProductDelivery(ProductDeliveryRepository productDeliveryRepository, ProductRepository productRepository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/product_delivery")
    Iterable<ProductDelivery> getProductDelivery() {
        return productDeliveryRepository.findAll();
    }

    @PostMapping("/product_delivery")
    ProductDelivery postProductDelivery(@RequestBody ProductDelivery productDelivery) {
        if (productRepository.existsById(productDelivery.getProduct().getId()))
            return productDeliveryRepository.save(productDelivery);
        else
            throw new IllegalArgumentException();
    }

}
