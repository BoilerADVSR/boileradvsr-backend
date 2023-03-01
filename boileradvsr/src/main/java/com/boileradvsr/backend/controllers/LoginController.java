package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.StudentRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMongoRepositories
@RequestMapping("/login")
public class LoginController {
    StudentRepository repository;

    public LoginController(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    @PutMapping ("/{id}")
    public String login(@RequestBody String email, String password) {
        Student student = repository.findStudentByEmail(email);
        if (student == null) {
            return "Student not found";
        } else {
            if (student.getPassword().equals(password)) {
                return "Welcome!";
            } else {
                return "Incorrect password";
            }
        }
    }
}
