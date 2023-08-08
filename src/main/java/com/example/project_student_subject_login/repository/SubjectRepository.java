package com.example.project_student_subject_login.repository;

import com.example.project_student_subject_login.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    // You can add custom query methods here if needed
}
