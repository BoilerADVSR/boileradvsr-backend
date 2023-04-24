package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.DegreeGraphRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    @PostMapping("/database/create")
    public ResponseEntity<DegreeGraph> createDegreeFromFile(@RequestBody String filename) throws IOException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = bfr.readLine()) != null) {
            lines.add(line);
        }
        String graphName = lines.get(0).substring(lines.get(0).indexOf(':') + 1);
        int vertices = Integer.parseInt(lines.get(1).substring(lines.get(1).indexOf(':') + 1));
        String[] classes = lines.get(2).substring(lines.get(2).indexOf(':') + 1).split(",");
        ArrayList<String> classNames = new ArrayList<>(Arrays.stream(classes).toList());
        DegreeGraph degreeGraph = new DegreeGraph(graphName ,vertices, classNames);

        for (int i = 3; i < lines.size(); i++) {
            line = lines.get(i);
            System.out.println(line);
            String[] vertexList = line.split(",");
            for (String vertex : vertexList) {
                String[] vertices1 = vertex.split(":");
                degreeGraph.addEdge(Integer.parseInt(vertices1[0]), Integer.parseInt(vertices1[1]));
            }
        }
        repository.save(degreeGraph);
        return ResponseEntity.ok(degreeGraph);
    }

}
