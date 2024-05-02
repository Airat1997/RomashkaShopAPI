package org.example.controller;

import java.util.UUID;
import org.example.model.ProductDelivery;
import org.example.model.ProductSale;
import org.example.repository.ProductDeliveryRepository;
import org.example.repository.ProductRepository;
import org.example.repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product_sale")
public class RestApiControllerProductSale {

    private final ProductSaleRepository productSaleRepository;
    private final ProductRepository productRepository;

    @Autowired
    RestApiControllerProductSale(ProductSaleRepository productSaleRepository,
            ProductRepository productRepository) {
        this.productSaleRepository = productSaleRepository;
        this.productRepository = productRepository;
    }
    @GetMapping
    ResponseEntity<Iterable<ProductSale>> getProductSale() {
        return new ResponseEntity<>(productSaleRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    ResponseEntity<ProductSale> getProductSaleById(@PathVariable("id") ProductSale productSale) {
        return new ResponseEntity<>(productSale, HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<ProductSale> postProductDelivery(@RequestBody ProductSale productSale) {
        if (productRepository.existsById(productSale.getProduct().getId())) {
            return new ResponseEntity<>(productSaleRepository.save(productSale), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @PutMapping("{id}")
    ResponseEntity<ProductSale> putProductDelivery(@PathVariable UUID id,
            @RequestBody ProductSale productSale) {
        if (!productSaleRepository.existsById(id)) {
            productSaleRepository.save(productSale);
            return new ResponseEntity<>(productSale, HttpStatus.CREATED);
        } else {
            productSaleRepository.save(productSale);
            return new ResponseEntity<>(productSale, HttpStatus.OK);
        }
    }
    @DeleteMapping("{id}")
    void deleteProductDelivery(@PathVariable UUID id) {
        productSaleRepository.deleteById(id);
    }
}
