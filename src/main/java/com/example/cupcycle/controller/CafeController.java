package com.example.cupcycle.controller;

import com.example.cupcycle.entity.Cafe;
import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    @GetMapping("/login")
    public ResponseEntity<ApiResponse<Cafe>> loginByNameAndCode(@RequestParam String name, @RequestParam int adminCode) {
        if(!cafeService.verifyAdminCode(adminCode))
        {
            ApiResponse<Cafe> response = new ApiResponse<>(false, 4004, "로그인 실패: 잘못된 관리자 코드입니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return cafeService.findCafeByName(name)
                .map(cafe -> {
                    ApiResponse<Cafe> response = new ApiResponse<>(true, 200, "로그인 성공", cafe);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    ApiResponse<Cafe> response = new ApiResponse<>(false, 4004, "로그인 실패: 해당 이름의 카페를 찾을 수 없습니다.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });

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
