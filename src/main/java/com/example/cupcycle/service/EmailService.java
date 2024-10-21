package com.example.cupcycle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    private final Map<String, String> emailVerificationCodes = new HashMap<>(); // 이메일-코드 매핑 저장

    // 인증 코드 생성
    public String generateVerificationCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    // 인증 이메일 전송
    public void sendVerificationEmail(String email) {
        String code = generateVerificationCode();
        emailVerificationCodes.put(email, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dlswns8679@naver.com");
        message.setTo(email);
        message.setSubject("인하대학교 이메일 인증 코드");
        message.setText("인하대학교 인증을 위해 다음 인증 코드를 입력하세요: " + code);

        emailSender.send(message);
    }

    // 코드 확인
    public boolean verifyCode(String email, String code) {
        String storedCode = emailVerificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }
}

