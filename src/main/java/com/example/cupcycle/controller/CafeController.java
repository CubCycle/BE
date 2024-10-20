package com.example.cupcycle.controller;

import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cafe")
public class CafeController {
    private final CafeService cafeService;
    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    /*
     * 카페의 다회용컵 재고 상태 조회
     */
    @GetMapping("/getStatus")
    public ResponseEntity<ApiResponse<Integer>> getAvailableCups(@RequestParam int id) {
        int availableCups = cafeService.getAvailableCups(id);

        // 카페에 다회용컵 재고가 없는 경우
        if (availableCups <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,5001, "해당 카페에 다회용컵 재고가 없습니다.", 0));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, 1000, "요청에 성공하였습니다.", availableCups));
    }

}