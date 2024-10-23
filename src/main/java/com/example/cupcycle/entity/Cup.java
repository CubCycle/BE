package com.example.cupcycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name = "cup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cupId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "returnStationId", nullable = false)
    private ReturnStation returnStation;

    @ManyToOne
    @JoinColumn(name = "cafeId", nullable = false)
    private Cafe cafe;

    @Column(nullable = false, unique = true)
    private String qrcode;  // 고유 QR 코드 필드 추가

    private Timestamp borrowTime;

    private Timestamp returnTime;

    @Enumerated(EnumType.STRING)
    private CupStatus status;

    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

    private java.sql.Timestamp deletedAt;

    public enum CupStatus {
        BORROWED, RETURNED, AVAILABLE;
    }
}
