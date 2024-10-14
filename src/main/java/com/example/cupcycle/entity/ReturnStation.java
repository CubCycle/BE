package com.example.cupcycle.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "returnStation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int returnStationId;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int current_cup;

    @Enumerated(EnumType.STRING)
    private ReturnStationStatus status;

    @CreationTimestamp
    private java.sql.Timestamp createdAt;

    @UpdateTimestamp
    private java.sql.Timestamp updatedAt;

    private java.sql.Timestamp deletedAt;

    public enum ReturnStationStatus {
        FULL, AVAILABLE;
    }
}
