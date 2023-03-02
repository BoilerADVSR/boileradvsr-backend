package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Course;
import com.boileradvsr.backend.models.CourseRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@EnableMongoRepositories
@RequestMapping("/courses")
public class CourseController {
    public CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Course> getCourses() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    @PostMapping
    public ResponseEntity createCourse(@RequestBody Course course) throws URISyntaxException {
        Course savedCourse = repository.save(course);
        return ResponseEntity.created(new URI("/courses/" + savedCourse.getCourseID())).body(savedCourse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
