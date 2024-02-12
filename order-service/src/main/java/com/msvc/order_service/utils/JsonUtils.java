package com.msvc.order_service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msvc.order_service.model.entities.OrderItems;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String orderItemsListToJson(OrderItems orderItems) {
        try {
            return objectMapper.writeValueAsString(orderItems);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir lista de OrderItem a JSON", e);
        }
    }
}