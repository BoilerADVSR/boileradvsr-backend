package com.boileradvsr.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//For demo purposes
@RestController
@RequestMapping("/aye")
public class ayeController {
    @GetMapping
    public ResponseEntity<String> print() {
        return ResponseEntity.ok("ayee");
    }
}
