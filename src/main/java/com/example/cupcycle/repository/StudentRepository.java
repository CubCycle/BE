package com.example.cupcycle.repository;

import com.example.cupcycle.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByEmail(String email);
    Optional<Student> findStudentByStudentId(int studentId);
    Optional<Student> findStudentByemail(String email);
}
