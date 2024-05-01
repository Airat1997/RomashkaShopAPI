package org.example.repository;

import org.example.model.ProductDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductDeliveryRepository extends JpaRepository<ProductDelivery, UUID> {
}
