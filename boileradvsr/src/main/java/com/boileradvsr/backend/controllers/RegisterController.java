package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@EnableMongoRepositories
public class RegisterController {
    StudentRepository repository;
    @PutMapping ("/register")
    public String register(String email, String conEmail, String password, String conPassword, String firstName, String lastName) {
        if (!email.equals(conEmail)) {
            return "Emails do not match";
        }
        if (!password.equals(conPassword)) {
            return "Passwords do not match";
        }
        if (emailCheck(email).equals("Invalid")) {
            return "Invalid email";
        }
        Student student = repository.findStudentByEmail(email);
        if (student == null) {
            Student newStudent = new Student(email, password, firstName, lastName);
            repository.save(newStudent);
            return "Welcome!";
        } else {
            return "Email already in use";
        }
    }
    public String emailCheck(String email){
        String regex = "^(.+)@purdue.edu$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return "Valid";
        }
        else{
            return "Invalid";
        }
    }
}
