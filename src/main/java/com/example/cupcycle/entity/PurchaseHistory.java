package com.example.cupcycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "purchase_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId; // 구매 ID

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // 구매한 학생

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 구매한 상품

    @Column(nullable = false)
    private Timestamp purchaseDate; // 구매 날짜
}
