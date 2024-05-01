package org.example.controller;

import org.example.repository.ProductDeliveryRepository;
import org.example.repository.ProductRepository;
import org.example.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RestApiControllerProductSale {

    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;

    @Autowired
    RestApiControllerProductSale(ProductSaleRepository productSaleRepository,
            ProductRepository productRepository) {
        this.productSaleRepository = productSaleRepository;
        this.productRepository = productRepository;
    }
}
