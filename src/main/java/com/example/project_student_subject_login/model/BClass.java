package com.example.project_student_subject_login.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_BClass")
public class BClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String ClassName;
    private String Teacher;

}
