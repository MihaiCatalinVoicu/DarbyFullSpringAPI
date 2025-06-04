package com.dev.demo.rest;

import com.dev.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            throw new StudentNotFoundException("Student id: " + studentId + " not found.");
        }

        return students.get(studentId);
    }
}
