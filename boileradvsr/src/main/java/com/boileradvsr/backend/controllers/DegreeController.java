package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Degree;
import com.boileradvsr.backend.models.DegreeRepository;
import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@EnableMongoRepositories
@RequestMapping("/degrees")
public class DegreeController {
    public DegreeRepository repository;

    public DegreeController(DegreeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Degree> getDegrees() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Degree getDegree(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    @PostMapping
    public ResponseEntity createDegree(@RequestBody Degree degree) throws URISyntaxException {
        Degree savedDegree = repository.save(degree);
        return ResponseEntity.created(new URI("/degrees/" + savedDegree.getDegreeTitle())).body(savedDegree);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDegree(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
