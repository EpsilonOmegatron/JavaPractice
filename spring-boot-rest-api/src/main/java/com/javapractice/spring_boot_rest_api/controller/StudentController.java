package com.javapractice.spring_boot_rest_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javapractice.spring_boot_rest_api.bean.Student;


@RestController
@RequestMapping("students")
public class StudentController {

    private List<Student> students;

    public StudentController() {
        students = new ArrayList<>();
        students.add(new Student(0, "Fir", "aga"));
        students.add(new Student(1, "Blizz", "aga"));
        students.add(new Student(2, "Thund", "aga"));
    }

    
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        return new ResponseEntity<>(students.get(id), HttpStatus.FOUND);
        // yes, i know "found" here refers to a location not a resource because of the 3xx coding, shut up
    }

    @GetMapping
    public List<Student> getStudents() {
        return students;
    }
    
    @GetMapping("query")
    public Student studentRequestParam(@RequestParam int id, @RequestParam String firstName){
        return new Student(id, firstName, null);
    }
    
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Student> createStudent(@RequestBody Student student) {
        students.add(new Student(student.getId(), student.getFirstName(), student.getLastName()));
        return students;
    }

    @PutMapping("{id}/update")
    public List<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
        students.get(id).setFirstName(student.getFirstName());
        students.get(id).setLastName(student.getLastName());
        return students;
    }

    @DeleteMapping("{id}/delete")
    public List<Student> deleteStudent(@PathVariable int id) {
        students.remove(id);
        return students;
    }

}
