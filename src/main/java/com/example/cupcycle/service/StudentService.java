package com.example.cupcycle.service;

import com.example.cupcycle.entity.Student;
import com.example.cupcycle.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void registerStudent(String name, String email, String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
        Student student = Student.builder()
                .name(name)
                .email(email)
                .password(password)
                .reward(0) // 초기 보상 포인트 설정
                .cupCount(0) // 초기 컵 사용 개수 설정
                .carbonReduction(0) // 초기 탄소 절감량 설정
                .build();
        studentRepository.save(student);
    }

    //이메일 중복 확인 메서드
    public boolean isEmailExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    // 이메일로 학생 찾기 메서드
    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

}

