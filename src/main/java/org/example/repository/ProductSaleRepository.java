package org.example.repository;

import java.util.UUID;
import org.example.model.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSaleRepository extends JpaRepository<ProductSale, UUID> {

}
