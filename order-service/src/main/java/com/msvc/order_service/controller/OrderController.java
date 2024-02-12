package com.msvc.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.msvc.order_service.model.dto.OrderRequest;
import com.msvc.order_service.model.dto.OrderResponse;
import com.msvc.order_service.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    
  @Autowired
  private OrderService orderService;

  @PostMapping
  @ResponseStatus( HttpStatus.CREATED )
  @CircuitBreaker(name = "orders-service", fallbackMethod = "placeOrderFallback")
  public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
    OrderResponse order = orderService.placedOrder(orderRequest);
    return ResponseEntity.ok(order);
  }

  @GetMapping
  @ResponseStatus( HttpStatus.OK )
  public List<OrderResponse> getOrders(){
    return orderService.getAllOrders();
  }

  @SuppressWarnings("unused")
  private ResponseEntity<OrderResponse> placeOrderFallback(OrderRequest orderRequest, Throwable throwable){
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
  }
}