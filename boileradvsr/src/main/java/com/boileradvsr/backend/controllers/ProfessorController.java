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
@RequestMapping("/professors")
public class ProfessorController {
    public ProfessorRepository repository;

    public ProfessorController(ProfessorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Professor getProfessor(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/{id}/courses")
    public List<Course> getProfessorCourses(@PathVariable String id) {
        Professor professor = repository.findById(id).orElseThrow(RuntimeException::new);
        return(professor.getCoursesTaught());
    }
    @PostMapping
    public ResponseEntity createProfessor(@RequestBody Professor professor) throws URISyntaxException {
        Professor savedProfessor = repository.save(professor);
        return ResponseEntity.created(new URI("/professor/" + savedProfessor.getId())).body(savedProfessor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDegree(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
