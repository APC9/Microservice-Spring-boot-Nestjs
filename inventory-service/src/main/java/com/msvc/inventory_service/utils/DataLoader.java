package com.msvc.inventory_service.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.msvc.inventory_service.model.entities.Inventory;
import com.msvc.inventory_service.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

  @Autowired
  private InventoryRepository inventoryRepository;
  
  @SuppressWarnings("null")
  @Override
  public void run(String... args) throws Exception {
    log.info("Loading data...");

    if( inventoryRepository.findAll().size() == 0){
      inventoryRepository.saveAll(
        List.of(
          Inventory.builder().sku("000001").quantity(10L).build(),
          Inventory.builder().sku("000002").quantity(20L).build(),
          Inventory.builder().sku("000003").quantity(30L).build(),
          Inventory.builder().sku("000004").quantity(0L).build()
        )
      );
    }

    log.info("Data loaded successfully");
  }

    
}