package com.msvc.order_service.events;

import com.msvc.order_service.model.dto.OrderItemsResponse;
import com.msvc.order_service.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, OrderItemsResponse OrderItemsResponse, OrderStatus orderStatus)  {
    
}