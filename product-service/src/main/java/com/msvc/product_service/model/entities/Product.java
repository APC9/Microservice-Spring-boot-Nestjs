package com.msvc.product_service.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String sku;
  private String description;
  private Double price;
  private Boolean status;

  @Override
  public String toString() {
    return "Product{" +
              "id='" + id + 
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              '}'; 
  }
}