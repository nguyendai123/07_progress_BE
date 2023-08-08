package com.example.project_student_subject_login.controller;

import com.example.project_student_subject_login.model.BClass;
import com.example.project_student_subject_login.model.Student;
import com.example.project_student_subject_login.model.Subject;
import com.example.project_student_subject_login.repository.BClassRepository;
import com.example.project_student_subject_login.repository.StudentRepository;
import com.example.project_student_subject_login.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BClassRepository bClassRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        // Set the BClass for the student
        if (student.getBClass() != null && student.getBClass().getID() != 0) {
            Optional<BClass> bClass = bClassRepository.findById(student.getBClass().getID());
            if (bClass.isPresent()) {
                student.setBClass(bClass.get());
            } else {
                return ResponseEntity.badRequest().body("Invalid BClass ID.");
            }
        }

        // Set the subjects for the student
        Set<Subject> subjects = new HashSet<>();
        for (Subject subject : student.getSubjects()) {
            Optional<Subject> existingSubject = subjectRepository.findById(subject.getID());
            if (existingSubject.isPresent()) {
                subjects.add(existingSubject.get());
            } else {
                return ResponseEntity.badRequest().body("Invalid Subject ID: " + subject.getID());
            }
        }
        student.setSubjects(subjects);

        studentRepository.save(student);
        return ResponseEntity.ok("Student created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setBirthday(updatedStudent.getBirthday());

            // Set the BClass for the student
            if (updatedStudent.getBClass() != null && updatedStudent.getBClass().getID() != 0) {
                Optional<BClass> bClass = bClassRepository.findById(updatedStudent.getBClass().getID());
                if (bClass.isPresent()) {
                    existingStudent.setBClass(bClass.get());
                } else {
                    return ResponseEntity.badRequest().body("Invalid BClass ID.");
                }
            }

            // Set the subjects for the student
            Set<Subject> subjects = new HashSet<>();
            for (Subject subject : updatedStudent.getSubjects()) {
                Optional<Subject> existingSubject = subjectRepository.findById(subject.getID());
                if (existingSubject.isPresent()) {
                    subjects.add(existingSubject.get());
                } else {
                    return ResponseEntity.badRequest().body("Invalid Subject ID: " + subject.getID());
                }
            }
            existingStudent.setSubjects(subjects);

            studentRepository.save(existingStudent);
            return ResponseEntity.ok("Student updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return ResponseEntity.ok("Student deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}