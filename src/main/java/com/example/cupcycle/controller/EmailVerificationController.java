package com.example.cupcycle.controller;

import com.example.cupcycle.service.ApiResponse;
import com.example.cupcycle.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailVerificationController {

    @Autowired
    private EmailService emailService;

    // 이메일로 인증 코드 전송
    @PostMapping("/send-code")
    public ResponseEntity<ApiResponse<String>> sendVerificationCode(@RequestParam String email) {
        if (email.endsWith("@inha.edu") || email.endsWith("@inha.ac.kr")) { // 이메일 도메인 확인
            emailService.sendVerificationEmail(email);
            ApiResponse<String> response = new ApiResponse<>(true, 1000, "인증 코드가 이메일로 전송되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<String> response = new ApiResponse<>(false, 5001, "유효한 인하대학교 이메일 주소가 아닙니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 인증 코드 확인
    @PostMapping("/verify-code")
    public ResponseEntity<ApiResponse<String>> verifyCode(@RequestParam String email, @RequestParam String code) {
        if (emailService.verifyCode(email, code)) {
            ApiResponse<String> response = new ApiResponse<>(true, 1000, "이메일 인증이 완료되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<String> response = new ApiResponse<>(false, 5002, "인증 코드가 올바르지 않습니다.");
            return ResponseEntity.badRequest().body(response);
        }
    }
}

