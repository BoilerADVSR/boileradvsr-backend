package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/degreegraphs")
public class DegreeGraphController {
    public DegreeGraphRepository repository;

    public DegreeGraphController(DegreeGraphRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public DegreeGraph getDegree(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}
