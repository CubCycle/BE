package com.example.cupcycle.repository;

import com.example.cupcycle.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {
    List<PurchaseHistory> findAll(); // 모든 구매 신청 목록 조회
}
