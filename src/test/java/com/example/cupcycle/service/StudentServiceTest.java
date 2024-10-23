package com.example.cupcycle.service;

import com.example.cupcycle.entity.Student;
import com.example.cupcycle.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void registerStudent_Successful() {
        String name = "injoon";
        String email = "dlswns8579@inha.edu";
        String password = "choi0344";

        studentService.registerStudent(name, email, password);

        Student student = studentRepository.findStudentByemail(email).orElse(null);
        assertNotNull(student);
        assertEquals(name, student.getName());
        assertEquals(email, student.getEmail());
        assertEquals(password, student.getPassword());
        assertEquals(0, student.getReward());
    }

    @Test
    void registerStudent_PasswordTooShort() {
        String name = "injoon";
        String email = "dlswns8579@inha.edu";
        String password = "12345";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            studentService.registerStudent(name, email, password);
        });
        assertEquals("비밀번호는 8자 이상이어야 합니다.", exception.getMessage());
    }

    @Test
    void isEmailExists() {
        // Arrange
        String email = "existing.email@example.com";
        studentService.registerStudent("Existing User", email, "password123");

        // Act
        boolean exists = studentService.isEmailExists(email);

        // Assert
        assertTrue(exists);
    }

    @Test
    void findStudentById() {
        // Arrange
        String email = "findme@example.com";
        studentService.registerStudent("Find Me", email, "password123");
        Student student = studentRepository.findStudentByemail(email).orElse(null);
        assertNotNull(student);

        // Act
        Student foundStudent = studentService.findStudentById(student.getStudentId()).orElse(null);

        // Assert
        assertNotNull(foundStudent);
        assertEquals(student.getStudentId(), foundStudent.getStudentId());
    }

    @Test
    void getStudentRewardById() {
        // Arrange
        String email = "reward@example.com";
        studentService.registerStudent("Reward User", email, "password123");
        Student student = studentRepository.findStudentByemail(email).orElse(null);
        assertNotNull(student);

        // Act
        int reward = studentService.getStudentRewardById(student.getStudentId()).orElse(0);

        // Assert
        assertEquals(0, reward);  // 새로 등록된 학생의 리워드는 0
    }














}