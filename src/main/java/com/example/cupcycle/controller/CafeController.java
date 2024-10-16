package com.example.cupcycle.controller;


import com.example.cupcycle.entity.Cafe;
import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CafeService cafeService;

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









}
