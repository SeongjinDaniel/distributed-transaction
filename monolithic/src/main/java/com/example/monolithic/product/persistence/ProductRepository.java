package com.example.monolithic.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.monolithic.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
