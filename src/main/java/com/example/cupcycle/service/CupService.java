package com.example.cupcycle.service;

import com.example.cupcycle.entity.Cafe;
import com.example.cupcycle.entity.Cup;
import com.example.cupcycle.entity.ReturnStation;
import com.example.cupcycle.entity.Student;
import com.example.cupcycle.repository.CafeRepository;
import com.example.cupcycle.repository.CupRepository;
import com.example.cupcycle.repository.ReturnStationRepository;
import com.example.cupcycle.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CupService {

    @Autowired
    private CafeRepository cafeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CupRepository cupRepository;
    @Autowired
    private ReturnStationRepository returnStationRepository;

    @Transactional
    public ApiResponse<String> borrowCup(int cafeId, int studentId, int cupId, double carbonIncrease)
    {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카페를 찾을 수 없습니다."));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalArgumentException("해당 학생을 찾을 수 없습니다."));
        Cup cup = cupRepository.findById(cupId)
                .orElseThrow(()->new IllegalArgumentException("해당 컵을 찾을 수 없습니다."));

        // 1. Cafe의 availableCup 감소
        if(cafe.getAvailableCups() <=0) {
            ApiResponse<String> response = new ApiResponse<>(false, 6001, "대여 가능한 컵이 없습니다.");
            return response;
        }
        cafe.setAvailableCups(cafe.getAvailableCups() - 1);
        cafeRepository.save(cafe);

        //2. Student의 cupCount 증가 및 carbonReduction 증가
        student.setCupCount(student.getCupCount() + 1);
        student.setCarbonReduction(student.getCarbonReduction() + carbonIncrease);
        studentRepository.save(student);

        //3.Cup의 상태 변경 및 borrowTime 갱신
        //Cup이 Available 상태가 아니면 빌릴 수 없음
        if(cup.getStatus() == Cup.CupStatus.AVAILABLE)
        {
            cup.setStatus(Cup.CupStatus.BORROWED);
            cup.setBorrowTime(Timestamp.valueOf(LocalDateTime.now()));
            cupRepository.save(cup);

            ApiResponse<String> response = new ApiResponse<>(true, 1000, "대여가 완료되었습니다.");
            return response;
        }
        else
        {
            ApiResponse<String> response = new ApiResponse<>(false, 6003, "사용 가능한 컵이 아닙니다.");
            return response;
        }
    }

    @Transactional
    public ApiResponse<String> returnCup(int cupId, int returnStationId) {
        Cup cup = cupRepository.findById(cupId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컵을 찾을 수 없습니다."));

        if (!cup.getStatus().equals(Cup.CupStatus.BORROWED)) {
            return new ApiResponse<>(false, 6002, "해당 컵은 반납 가능한 상태가 아닙니다.");
        }

        ReturnStation returnStation = returnStationRepository.findById(returnStationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 반납기를 찾을 수 없습니다."));
        Student student = cup.getStudent();

        // 1. Cup의 상태 변경 및 returnTime 갱신
        cup.setStatus(Cup.CupStatus.RETURNED);
        cup.setReturnTime(Timestamp.valueOf(LocalDateTime.now()));
        cupRepository.save(cup);

        // 2. ReturnStation의 current_cup 증가
        returnStation.setCurrent_cup(returnStation.getCurrent_cup() + 1);
        returnStationRepository.save(returnStation);

        // 3. Student의 reward 증가
        student.setReward(student.getReward() + 300);
        studentRepository.save(student);

        return new ApiResponse<>(true, 1000, "반납이 완료되었습니다.");
    }




}
