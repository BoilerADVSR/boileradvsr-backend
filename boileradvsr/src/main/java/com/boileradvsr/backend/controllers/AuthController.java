//package com.boileradvsr.backend.controllers;
//
//import com.boileradvsr.backend.models.AuthReponse;
//import com.boileradvsr.backend.models.Student;
//import com.boileradvsr.backend.models.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import com.boileradvsr.backend.models.AuthRequest;
//
//@RestController
//public class AuthController {
//    @Autowired
//    private StudentRepository repository;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/register")
//    private ResponseEntity<?> register(@RequestBody AuthRequest ar) {
//        String username = ar.getUsername();
//        String password = ar.getPassword();
//        Student student = new Student();
//        student.setEmail(username);
//        student.setPassword(password);
//        try{
//            repository.save(student);
//        } catch (Exception e) {
//            return ResponseEntity.ok(new AuthReponse("User already exists" + username));
//        }
//        return ResponseEntity.ok(new AuthReponse("Student account created successfully" + username));
//    }
//    @GetMapping("/rfrg")
//    private ResponseEntity<?> login(@RequestBody AuthRequest ar) {
//        String username = ar.getUsername();
//        String password = ar.getPassword();
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.ok(new AuthReponse("Error logging in" + username));
//        }
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        return ResponseEntity.ok(new AuthReponse("Successful Login for user" + username));
//    }
//}
