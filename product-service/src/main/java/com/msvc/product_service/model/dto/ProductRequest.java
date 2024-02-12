package com.msvc.product_service.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
  private String name;
  private String sku;
  private String description;
  private Double price;
  private Boolean status;
}