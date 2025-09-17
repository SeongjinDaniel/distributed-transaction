package com.example.monolithic.init;

import org.springframework.stereotype.Component;

import com.example.monolithic.point.domain.Point;
import com.example.monolithic.point.infrastructure.PointRepository;
import com.example.monolithic.product.domain.Product;
import com.example.monolithic.product.persistence.ProductRepository;

import jakarta.annotation.PostConstruct;

@Component
public class TestDataCreator {

  private final PointRepository pointRepository;

  private final ProductRepository productRepository;

  public TestDataCreator(PointRepository pointRepository, ProductRepository productRepository) {
	this.pointRepository = pointRepository;
	this.productRepository = productRepository;
  }

  @PostConstruct
  public void createTestDatta() {
	pointRepository.save(new Point(1L, 10000L));

	Product product1 = new Product(100L, 100L);
	Product product2 = new Product(100L, 200L);

	productRepository.save(product1);
	productRepository.save(product2);
  }
}
