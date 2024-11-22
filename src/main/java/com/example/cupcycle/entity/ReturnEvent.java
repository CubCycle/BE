package com.example.cupcycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "returnEvent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    @Column(nullable = false)
    private int returnStationId;

    @CreationTimestamp
    private java.sql.Timestamp returnedAt;
}

