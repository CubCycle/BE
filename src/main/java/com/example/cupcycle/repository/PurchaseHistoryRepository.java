package com.example.cupcycle.repository;

import com.example.cupcycle.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {
}
