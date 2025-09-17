package com.example.monolithic.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.monolithic.order.application.OrderService;
import com.example.monolithic.order.application.RedisLockService;
import com.example.monolithic.order.application.dto.CreateOrderResult;
import com.example.monolithic.order.controller.dto.CreateOrderRequest;
import com.example.monolithic.order.controller.dto.CreateOrderResponse;
import com.example.monolithic.order.controller.dto.PlaceOrderRequest;
import com.example.monolithic.order.infrastructure.OrderRepository;

@RestController
public class OrderController {

	private final OrderService orderService;

	private final RedisLockService redisLockService;

	public OrderController(
		OrderService orderService,
		RedisLockService redisLockService
	) {
		this.orderService = orderService;
	  this.redisLockService = redisLockService;
	}

	@PostMapping("/order")
	public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
		CreateOrderResult result = orderService.createOrder(request.toCreateOrderCommand());

		return new CreateOrderResponse(result.orderId());
	}

	@PostMapping("/order/place")
	public void placeOrder(
		@RequestBody PlaceOrderRequest request
	) throws InterruptedException {
	  String key = "order:monolithic:" + request.orderId();
	  boolean acquiredLock = redisLockService.tryLock(key, request.orderId().toString());

	  if (!acquiredLock) {
		throw new IllegalStateException("락획득에 실패하였습니다.");
	  }

	  try {
		orderService.placeOrder(request.toPlaceOrderCommand());
	  } finally {
		redisLockService.releaseLock(key);
	  }
	}
}
