package com.example.project_student_subject_login.repository;

import com.example.project_student_subject_login.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    // You can add custom query methods here if needed
}
