package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.CourseRepository;
import com.boileradvsr.backend.models.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/degrees")
public class DegreeController {
    public DegreeRepository repository;
    @Autowired
    public CourseRepository courseRepository;

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

    @GetMapping("/alldept")
    public List<String> getAllDepartments() {
        List<Degree> degrees = repository.findAll();
        ArrayList<String> departmentNames = new ArrayList<>();
        for (Degree degree : degrees) {
            departmentNames.add(degree.getDepartment());
        }
        return departmentNames.stream().distinct().toList();
    }

    @PostMapping
    public ResponseEntity createDegree(@RequestBody Degree degree) throws URISyntaxException {
        Degree savedDegree = repository.save(degree);
        return ResponseEntity.created(new URI("/degrees/" + savedDegree.getDegreeTitle())).body(savedDegree);
    }

    @PostMapping("/database/create")
    public ResponseEntity<Degree> createDegreeFromFile(@RequestBody String filename) throws IOException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = bfr.readLine()) != null) {
            lines.add(line);
        }
        String degreeName = lines.get(0).substring(lines.get(0).indexOf(':') + 1);
        Degree.DEGREETYPE degreetype = Degree.DEGREETYPE.valueOf(lines.get(1).substring(lines.get(1).indexOf(':') + 1).toUpperCase());
        String college = lines.get(2).substring(lines.get(2).indexOf(':') + 1);
        String department = lines.get(3).substring(lines.get(3).indexOf(':') + 1);
        Degree degree = new Degree(degreetype,degreeName, college, department);

        Requirement.Type reqType = Requirement.Type.CORE;

        for (int i = 4; i < lines.size(); i++) {
            line = lines.get(i);
            if (line.equals("CORE")) {
                reqType = Requirement.Type.CORE;
                continue;
            } else if (line.equals("ELECTIVE")) {
                reqType = Requirement.Type.ELECTIVE;
                continue;
            }
            int semi = line.indexOf(":");
            String classList = line.substring(semi + 1);
            String requirementName = line.substring(0, semi);
            ArrayList<Course> classes = new ArrayList<>();
            for (String courseID : classList.split(",")) {
                //System.out.println(courseID);
                classes.add(courseRepository.findById(courseID).orElseThrow(RuntimeException::new));
            }
            degree.addRequirement(new Requirement(requirementName,reqType,classes));
        }
        repository.save(degree);
        return ResponseEntity.ok(degree);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDegree(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
