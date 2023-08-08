package com.example.project_student_subject_login.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Stu_Sub")
public class Student_Sub {
    @Id
    private int Id_Stu;

    @Id
    private int Id_Sub ;

    private int mark3;
    private int mark2;
}
