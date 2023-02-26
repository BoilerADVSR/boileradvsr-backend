package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class LoginController {
    StudentRepository repository;
    @PutMapping ("/login")
    public String login(String email, String password) {
        Student student = repository.findByEmail(email);
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
