package com.example.cupcycle.service;

import com.example.cupcycle.entity.Product;
import com.example.cupcycle.entity.PurchaseHistory;
import com.example.cupcycle.entity.Student;
import com.example.cupcycle.repository.ProductRepository;
import com.example.cupcycle.repository.PurchaseHistoryRepository;
import com.example.cupcycle.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class PurchaseService {
    private final StudentRepository studentRepository;
    private final ProductRepository productRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired
    public PurchaseService(StudentRepository studentRepository,
                           ProductRepository productRepository,
                           PurchaseHistoryRepository purchaseHistoryRepository) {
        this.studentRepository = studentRepository;
        this.productRepository = productRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Transactional
    public PurchaseHistory purchaseProduct(int studentId, int productId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("학생을 찾을 수 없습니다."));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        int productPrice = product.getPrice();

        if (student.getReward() < productPrice) {
            throw new RuntimeException("리워드 포인트가 부족합니다.");
        }

        // 구매 이력 생성
        PurchaseHistory purchaseHistory = PurchaseHistory.builder()
                .student(student)
                .product(product)
                .purchaseDate(new Timestamp(System.currentTimeMillis()))
                .build();

        return purchaseHistoryRepository.save(purchaseHistory);
    }
}
