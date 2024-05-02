package org.example.repository;
import java.util.List;
import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPriceAsc();
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findAllSortedByPriceDesc();
    @Query("SELECT p FROM Product p ORDER BY p.name ASC")
    List<Product> findAllSortedByNameAsc();
    @Query("SELECT p FROM Product p ORDER BY p.name DESC")
    List<Product> findAllSortedByNameDesc();

    Iterable<Product> findByNameContaining(String name);
    Iterable<Product> findByPrice(Double price);
    Iterable<Product> findByProductAvailability(boolean productAvailability);
    Iterable<Product> findByPriceLessThan(Double price);
    Iterable<Product> findByPriceGreaterThan(Double price);
}
