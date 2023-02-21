package com.boileradvsr.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PutMapping ("/login")
    public String login(String username, String password) {
        return "abc";
    }
}
