package com.msvc.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msvc.product_service.model.dto.ProductRequest;
import com.msvc.product_service.model.dto.ProductResponse;
import com.msvc.product_service.model.entities.Product;
import com.msvc.product_service.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @SuppressWarnings("null")
  public void addProduct(ProductRequest productRequest) {
    Product product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();
    
    productRepository.save(product);
    log.info("Product added: {}", product);
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();

    return products.stream().map(this::mapToProductResponse).toList();
  }

  private ProductResponse mapToProductResponse (Product product) {
    return ProductResponse.builder()
      .sku(product.getSku())
      .name(product.getName())
      .description(product.getDescription())
      .price(product.getPrice())
      .status(product.getStatus())
      .build();
  }
    
}