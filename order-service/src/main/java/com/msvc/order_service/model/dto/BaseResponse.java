package com.msvc.order_service.model.dto;


public record BaseResponse(String[] errorMessage) {
    
  public boolean hasErrors(){
    return errorMessage != null && errorMessage.length > 0;
  }
}