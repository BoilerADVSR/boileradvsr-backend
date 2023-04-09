package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Requirement;
import com.boileradvsr.backend.models.repositories.RequirementRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/requirements")
public class RequirementController {
    public RequirementRepository repository;

    public RequirementController(RequirementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Requirement> getRequirements() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Requirement getRequirement(@PathVariable String id) {
        return repository.findRequirementByName(id);
    }

    @PostMapping
    public ResponseEntity createRequirement(@RequestBody Requirement requirement) throws URISyntaxException {
        Requirement savedRequirement = repository.save(requirement);
        return ResponseEntity.created(new URI("/requirements/" + savedRequirement.getName())).body(savedRequirement);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRequirement(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
