package com.example.project_student_subject_login.repository;

import com.example.project_student_subject_login.model.BClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BClassRepository extends JpaRepository<BClass, Integer> {
    // You can add custom query methods here if needed
}
