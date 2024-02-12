package com.msvc.notification_service.events;

import com.msvc.notification_service.model.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemsCount, OrderStatus orderStatus)  {
    
}