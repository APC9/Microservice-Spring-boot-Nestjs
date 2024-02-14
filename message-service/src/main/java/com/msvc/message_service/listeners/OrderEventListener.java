package com.msvc.message_service.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import com.msvc.message_service.events.OrderEvent;
import com.msvc.message_service.utils.JsonUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderEventListener {

  @RabbitListener(queues ="${rabbitmq.queue.name}")
  public void handleOrdersNotifications(String message){
    OrderEvent orderEvent = JsonUtils.fromJson(message, OrderEvent.class);
    //Send email to customer, send SMS to customer, etc.
    //Notify another service...
    log.info("Order {} event received for order : {} with {} items", orderEvent.orderStatus(), orderEvent.orderNumber(), orderEvent.OrderItemsResponse());
  }
}