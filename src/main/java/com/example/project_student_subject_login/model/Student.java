package com.example.project_student_subject_login.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
@Data
@Entity
@Table(name = "tb_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String Name;
    private String Gender;
    private Date Birthday;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Bclass", referencedColumnName = "ID")
    private BClass BClass;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)

    @JoinTable(
            name = "Stu_Sub",

            joinColumns = @JoinColumn(name = "Id_Stu",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="Id_Sub", referencedColumnName = "ID")
    )
    private Set<Subject> subjects= new HashSet<>();


}

