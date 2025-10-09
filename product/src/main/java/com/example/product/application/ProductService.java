package com.example.product.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.application.dto.ProductReserveCommand;
import com.example.product.application.dto.ProductReserveResult;
import com.example.product.domain.Product;
import com.example.product.domain.ProductReservation;
import com.example.product.persistence.ProductRepository;
import com.example.product.persistence.ProductReservationRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  private final ProductReservationRepository productReservationRepository;

  public ProductService(ProductRepository productRepository, ProductReservationRepository productReservationRepository) {
	this.productRepository = productRepository;
	this.productReservationRepository = productReservationRepository;
  }

  @Transactional
  public ProductReserveResult tryReserve(ProductReserveCommand command) {
	List<ProductReservation> exists = productReservationRepository.findAllByRequestId(command.requestId());

	if(!exists.isEmpty()) {
	  long totalPrice = exists.stream().mapToLong(ProductReservation::getReservedPrice).sum();

	  return new ProductReserveResult(totalPrice);
	}

	for ProductReserveCommand.ReserveItem item : command.items()) {
	  Product product = productRepository.findById(item.productId()).orElseThrow();


	}
  }
}
