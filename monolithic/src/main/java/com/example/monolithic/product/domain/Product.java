package com.example.monolithic.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long quantity;

  private Long price;

  private Long reservedQuantity;

  public Product() {
  }

  public Long reserve(Long requestedQuantity) {
	long reservableQuantity = this.quantity - this.reservedQuantity;

	if (reservableQuantity < requestedQuantity) {
	  throw new IllegalArgumentException("재고가 부족합니다.");
	}

	reservableQuantity += requestedQuantity;

	return price * requestedQuantity;
  }

  public Product(Long quantity, Long price) {
	this.quantity = quantity;
	this.price = price;
  }

  public Long calculatePrice(Long quantity) {
	return price * quantity;
  }

  public void buy(Long quantity) {
	if (this.quantity < quantity) {
	  throw new IllegalArgumentException("재고가 부족합니다.");
	}

	this.quantity = this.quantity - quantity;
  }
}
