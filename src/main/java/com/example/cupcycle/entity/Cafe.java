package com.example.cupcycle.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cafeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer availableCups;

    @Enumerated(EnumType.STRING)
    private CafeStatus status;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public enum CafeStatus {
        AVAILABLE, EMPTY
    }

    //카페의 컵 재고 1 감소
    public void decreaseAvailableCups() {
        this.availableCups--;
    }

    //카페의 컵 재고 1 증가
    public void increaseAvailableCups() {this.availableCups++;}


}
