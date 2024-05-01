package org.example.controller;

import org.example.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class RestApi {
    public final ProductRepository productRepository;
    public final JpaRepository repository;

    public RestApi(ProductRepository productRepository, JpaRepository repository) {
        this.productRepository = productRepository;
        this.repository = repository;
    }

    Iterable<?> getDocuments() {
        return repository.findAll();
    }

    //@GetMapping("/product_delivery/{id}")
    public <T> T getDocumentById(@PathVariable("id") T document) {
        return document;
    }

}
