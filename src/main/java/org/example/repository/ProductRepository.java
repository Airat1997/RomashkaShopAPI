package org.example.repository;
import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Iterable<Product> findByNameContaining(String name);
    Iterable<Product> findByPrice(Double price);
    Iterable<Product> findByProductAvailability(boolean productAvailability);
    Iterable<Product> findByPriceLessThan(Double price);
    Iterable<Product> findByPriceGreaterThan(Double price);
}
