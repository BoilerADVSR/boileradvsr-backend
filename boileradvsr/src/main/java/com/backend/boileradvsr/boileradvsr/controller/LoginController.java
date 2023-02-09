package com.backend.boileradvsr.boileradvsr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @GetMapping("/login")
    public String login() {
        return "Welcome to the login page";
    }
    
    @GetMapping("/login/{user}")
    public String login(@PathVariable(value="user") String user) {
        return "Hello, " + user;
    }
}
