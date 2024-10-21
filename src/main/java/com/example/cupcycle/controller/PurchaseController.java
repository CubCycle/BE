package com.example.cupcycle.controller;

import com.example.cupcycle.entity.PurchaseHistory;
import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /*
    * 상품 구매 신청
     */
    @PostMapping("/requestPurchase")
    public ResponseEntity<ApiResponse<PurchaseHistory>> purchaseProduct(
            @RequestParam int studentId,
            @RequestParam int productId) {
        try {
            PurchaseHistory purchaseHistory = purchaseService.purchaseProduct(studentId, productId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(true,200, "물품 교환 신청이 완료되었습니다.", purchaseHistory));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,6001, e.getMessage()));
        }
    }
}
