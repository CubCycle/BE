package com.example.cupcycle.controller;

import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.ReturnStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/returnStation")
public class ReturnStationController {
    private final ReturnStationService returnStationService;

    @Autowired
    public ReturnStationController(ReturnStationService returnStationService) {
        this.returnStationService = returnStationService;
    }

    /*
    * 반납대의 재고 상태 조회
    */

    @GetMapping("/getStatus")
    public ResponseEntity<ApiResponse<Integer>> getAvailableCups(@RequestParam int id) {
        int availableCups = returnStationService.getAvailableCups(id);

        if (availableCups <= 0) {  // 반납대가 꽉찬 경우 (재고 X)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,5001, "해당 반납대에 반납할 수 없습니다.", 0));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, 1000, "요청에 성공하였습니다.",availableCups));
    }

}
