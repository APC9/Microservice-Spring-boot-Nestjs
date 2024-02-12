package com.msvc.order_service.model.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "orders") 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    @OneToMany( mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
}