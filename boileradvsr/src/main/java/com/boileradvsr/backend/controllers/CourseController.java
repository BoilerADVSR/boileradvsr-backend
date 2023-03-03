package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.Course;
import com.boileradvsr.backend.models.CourseRepository;
import com.boileradvsr.backend.models.Degree;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


@RestController
@EnableMongoRepositories
@RequestMapping("/courses")
public class CourseController {
    public CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }
    /*
    Requests should be in the format of: fetch('/courses?department=CS')
    In order to "sort" by rating pass the param as 0.0, '/courses?rating=0.0'
    in the future you can pass multiple params by doing '/courses?department=CS&avg-gpa=3.5'
     */

    @RequestMapping
    public List<Course> getCourses(@RequestParam Map<String, String> params) {
        if (params.containsKey("department")) {
            return (repository.findCoursesByCourseIdDepartment(params.get("department")));
        }
        if (params.containsKey("avg-gpa")) {
            return (repository.findCoursesByAverageGPAGreaterThanEqualOrderByAverageGPADesc(Double.parseDouble(params.get("avg-gpa"))));
        }
        if (params.containsKey("rating")) {
            return (repository.findCoursesByAverageRatingOrderByAverageRatingDesc(Double.parseDouble(params.get("rating"))));
        }
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

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable String courseId, @RequestBody Course course) {
        Course currentCourse = repository.findCourseByCourseID(courseId);
        currentCourse.setCollege(course.getCollege());
        currentCourse.setCourseTitle(course.getCourseTitle());
        currentCourse.setDepartment(course.getDepartment());
        currentCourse.setCourseID(course.getCourseID());
        currentCourse = repository.save(course);

        return ResponseEntity.ok(currentCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
