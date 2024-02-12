package com.msvc.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.msvc.product_service.model.dto.ProductRequest;
import com.msvc.product_service.model.dto.ProductResponse;
import com.msvc.product_service.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;
    
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addProduct(@RequestBody ProductRequest productRequest) {
    productService.addProduct(productRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponse> getAllProducts(){
    return productService.getAllProducts();
  }

}