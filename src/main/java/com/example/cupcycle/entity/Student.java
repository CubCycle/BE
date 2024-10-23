package com.example.cupcycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @Column(nullable = false, length = 50)
    private String name; // 학생 이름

    @Column(nullable = false, length = 50, unique = true)
    private String email; // 이메일

    @Column(nullable = false, unique = true)
    private String password; // 비밀번호

    @Column(nullable = false)
    private Integer reward; // 보상 포인트

    @Column(nullable = false)
    private Integer cupCount; // 사용한 컵 개수

    @Column(nullable = false)
    private double carbonReduction; // 절감한 탄소량

    @CreationTimestamp
    private Timestamp createdAt; // 생성 시각

    @UpdateTimestamp
    private Timestamp updatedAt; // 수정 시각

    private Timestamp deletedAt; // 삭제 시각

    //사용한 컵 개수를 1개 증가시킴
    public void increaseCupCount() {
        this.cupCount++;
    }

    public void increaseCarbonReduction(double carbonReduction) {
        this.carbonReduction += carbonReduction;
    }

    public void increaseReward(int reward) {
        this.reward += reward;
    }
}
