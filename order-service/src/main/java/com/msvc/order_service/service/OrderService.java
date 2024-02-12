package com.msvc.order_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.msvc.order_service.events.OrderEvent;
import com.msvc.order_service.model.dto.BaseResponse;
import com.msvc.order_service.model.dto.OrderItemRequest;
import com.msvc.order_service.model.dto.OrderItemsResponse;
import com.msvc.order_service.model.dto.OrderRequest;
import com.msvc.order_service.model.dto.OrderResponse;
import com.msvc.order_service.model.entities.Order;
import com.msvc.order_service.model.entities.OrderItems;
import com.msvc.order_service.model.enums.OrderStatus;
import com.msvc.order_service.repository.OrderRepository;
import com.msvc.order_service.utils.JsonUtils;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private WebClient.Builder webClientBuilder;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @SuppressWarnings("null")
  public OrderResponse placedOrder(OrderRequest orderRequest) {

    BaseResponse result = webClientBuilder.build()
      .post()
      .uri( "lb://inventory-service/api/inventory/in-stock")
      .bodyValue(orderRequest.getOrderItems())
      .retrieve()
      .bodyToMono(BaseResponse.class)
      .block();

    if(result != null && !result.hasErrors() ) {
      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());
      order.setOrderItems( orderRequest.getOrderItems().stream()
        .map( orderItemRequest -> mapOrderItemRequestToOrderItem( orderItemRequest, order))
        .toList());

      Order savedOrder = orderRepository.save(order);
      OrderResponse orderResponse = mapToOrderResponse(savedOrder);
      System.out.println(orderResponse);

      //TODO:  Send message to order topic

      kafkaTemplate.send("orders-topic", JsonUtils.toJson(
        new OrderEvent(savedOrder.getOrderNumber(), orderResponse.orderItems().get(0), OrderStatus.PLACED)
      ));

      return orderResponse;
    }else{
      throw new IllegalArgumentException("Some of products are not in stock");
    }
    
  }

  public List<OrderResponse> getAllOrders(){
    List<Order> orders = orderRepository.findAll();
    return orders.stream().map(this::mapToOrderResponse).toList();
  }

  private OrderResponse mapToOrderResponse(Order order){
    return new OrderResponse(order.getId(), order.getOrderNumber(), 
      order.getOrderItems().stream().map(this::mapToOrderItemRequest).toList());
  }

  private OrderItemsResponse mapToOrderItemRequest( OrderItems orderItems){
    return new OrderItemsResponse(orderItems.getId(), orderItems.getSku(), orderItems.getPrice(), orderItems.getQuantity() );
  }

  private OrderItems mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, Order order){
    return OrderItems.builder()
      .id(orderItemRequest.getId())
      .sku(orderItemRequest.getSku())
      .price(orderItemRequest.getPrice())
      .quantity(orderItemRequest.getQuantity())
      .order(order)
      .build();
  }  
    
}