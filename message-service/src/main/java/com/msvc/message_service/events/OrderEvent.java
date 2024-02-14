package com.msvc.message_service.events;

import com.msvc.message_service.model.dto.OrderItemsResponse;
import com.msvc.message_service.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, OrderItemsResponse OrderItemsResponse, OrderStatus orderStatus)  {
    
}