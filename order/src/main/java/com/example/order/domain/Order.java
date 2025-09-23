package com.example.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderStatus statusCode;

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public OrderStatus getStatusCode() {
		return statusCode;
	}

	public void complete() {
		this.statusCode = OrderStatus.COMPLETED;
	}

	public enum OrderStatus {
		CREATED, COMPLETED, CANCELLED
	}
}
