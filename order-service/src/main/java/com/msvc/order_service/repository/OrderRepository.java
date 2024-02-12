package com.msvc.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msvc.order_service.model.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}