package com.example.monolithic.order.application.dto;

import java.util.List;

public record CreateOrderCommand(
	List<OrderItems> orderItems
) {
  public record OrderItems(
	  	Long productId,
		Long quantity
  ) {}
}
