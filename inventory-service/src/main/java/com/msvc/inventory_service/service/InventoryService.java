package com.msvc.inventory_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msvc.inventory_service.model.dto.BaseResponse;
import com.msvc.inventory_service.model.dto.OrderItemRequest;
import com.msvc.inventory_service.model.entities.Inventory;
import com.msvc.inventory_service.repository.InventoryRepository;

@Service
public class InventoryService {

  @Autowired
  private InventoryRepository inventoryRepository;

  public boolean isInStock(String sku) {
    Optional<Inventory> inventory = inventoryRepository.findBySku(sku); 

    return inventory.filter( value -> value.getQuantity() > 0).isPresent();
  }

  
  public BaseResponse areInStock(List<OrderItemRequest> orderItems) {
    List<String> errorList = new ArrayList<String>(); 

    List<String> skus = orderItems.stream().map(OrderItemRequest::getSku).toList();

    List<Inventory> inventoryList =  inventoryRepository.findBySkuIn(skus);

    orderItems.forEach( orderItem -> {
      Optional<Inventory>  inventory = inventoryList.stream().filter( value -> value.getSku().equals(orderItem.getSku())).findFirst();

      if( inventory.isEmpty() ){
        errorList.add("Product with sku"+ orderItem.getSku() + "does not exist");
      }else if( inventory.get().getQuantity() < orderItem.getQuantity()){
        errorList.add("Product with sku"+ orderItem.getSku() + "has insufficient quantity");
      }
    });

    return errorList.size() > 0 
      ? new BaseResponse( errorList.toArray( new String[0]) )
      : new BaseResponse(null); 

  }
    
}