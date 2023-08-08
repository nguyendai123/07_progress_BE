package com.example.project_student_subject_login.service;

import com.example.project_student_subject_login.model.BClass;
import com.example.project_student_subject_login.model.Student;
import com.example.project_student_subject_login.model.Subject;
import com.example.project_student_subject_login.repository.BClassRepository;
import com.example.project_student_subject_login.repository.StudentRepository;
import com.example.project_student_subject_login.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final BClassRepository bClassRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, BClassRepository bClassRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.bClassRepository = bClassRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void createStudent(Student student) {
        setBClassForStudent(student);
        setSubjectsForStudent(student);
        studentRepository.save(student);
    }

    public void updateStudent(int id, Student updatedStudent) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setBirthday(updatedStudent.getBirthday());
            setBClassForStudent(updatedStudent);
            setSubjectsForStudent(updatedStudent);
            studentRepository.save(existingStudent);
        }
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    private void setBClassForStudent(Student student) {
        if (student.getBClass() != null && student.getBClass().getID() != 0) {
            Optional<BClass> bClass = bClassRepository.findById(student.getBClass().getID());
            bClass.ifPresent(student::setBClass);
        }
    }

    private void setSubjectsForStudent(Student student) {
        for (Subject subject : student.getSubjects()) {
            Optional<Subject> existingSubject = subjectRepository.findById(subject.getID());
            existingSubject.ifPresent(student.getSubjects()::add);
        }
    }
}
