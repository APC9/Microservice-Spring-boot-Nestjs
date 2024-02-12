package com.msvc.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.msvc.inventory_service.model.dto.BaseResponse;
import com.msvc.inventory_service.model.dto.OrderItemRequest;
import com.msvc.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
  @Autowired
  private InventoryService inventoryService;

  @GetMapping("/{sku}")
  @ResponseStatus( HttpStatus.OK)
  public boolean isInStock(@PathVariable String sku) {
    return inventoryService.isInStock(sku);
  }

  @PostMapping("/in-stock")
  @ResponseStatus( HttpStatus.OK)
  public BaseResponse areInStock(@RequestBody List<OrderItemRequest> orderItems){
    System.out.println(orderItems);
    return inventoryService.areInStock(orderItems);
  }

}