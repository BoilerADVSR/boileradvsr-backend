package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@EnableMongoRepositories
@RequestMapping("/degrees")
public class DegreeController {
    public DegreeRepository repository;

    public DegreeController(DegreeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Degree getDegree(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    @RequestMapping
    public List<Degree> getDegrees(@RequestParam Map<String, String> params) {
        if (params.containsKey("degree-type") && params.containsKey("department")) {
            return (repository.findDegreesByDepartmentAndDegreeType(params.get("department"), Degree.DEGREETYPE.valueOf(params.get("degree-type"))));
        }
        if (params.containsKey("degree-type")) {
            return (repository.findDegreesByDegreeType(Degree.DEGREETYPE.valueOf(params.get("degree-type"))));
        }
        if (params.containsKey("department")) {
            return (repository.findDegreesByDepartment(params.get("department")));
        }
        if (params.containsKey("college")) {
            return (repository.findDegreesByCollege(params.get("college")));
        }
        return repository.findAll();
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
