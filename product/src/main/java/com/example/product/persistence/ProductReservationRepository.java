package com.example.product.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.domain.ProductReservation;

public interface ProductReservationRepository extends JpaRepository<ProductReservation, Long> {
  List<ProductReservation> findAllByRequestId(String requestId);
}
