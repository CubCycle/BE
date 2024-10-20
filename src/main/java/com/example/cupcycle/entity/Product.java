package com.example.cupcycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId; // 상품 ID

    @Column(nullable = false, length = 100)
    private String name; // 상품 이름

    @Column(nullable = false)
    private Integer price; // 상품 가격
}
