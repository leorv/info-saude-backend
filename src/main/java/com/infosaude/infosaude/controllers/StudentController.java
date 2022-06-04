package com.infosaude.infosaude.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.infosaude.infosaude.entities.Student;
import com.infosaude.infosaude.services.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "students")
public class StudentController {
    
    public StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public String createStudent(@RequestBody Student student) throws InterruptedException, ExecutionException {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() throws InterruptedException, ExecutionException {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        System.out.println(id);
        return studentService.getStudentById(id);
    }

    @GetMapping("/getByName/{name}")
    public List<Student> getStudentByName(@PathVariable(value = "name") String name)
            throws InterruptedException, ExecutionException {
        return studentService.getStudentByName(name);
    }

    @PutMapping("/update")
    // public String updateStudent(@RequestParam Student student) throws
    // InterruptedException, ExecutionException {
    public String updateStudent(@RequestBody Student student) throws InterruptedException, ExecutionException {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndPoint() {
        return ResponseEntity.ok("Test get student end-point is working.");
    }
}
