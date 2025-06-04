package com.dev.demo.rest;

import com.dev.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void loadData() {

        students = new ArrayList<Student>();

        students.add(new Student("Ion", "Mihalache"));
        students.add(new Student("Ana", "Ciocan"));
        students.add(new Student("Cristi", "Ionescu"));

    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId > students.size() || (studentId < 0) ) {
            throw new StudentNotFoundException("Student id: " + " not found.");
        }

        return students.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e) {

        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
