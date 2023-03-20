package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.coyote.Response;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@EnableMongoRepositories
@RequestMapping("/students")
public class StudentController {
    @Autowired
    public DegreeGraphController degreeGraphController;

    @Autowired
    public CourseRepository courseRepository;

    public StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @GetMapping("/login")
    public Student login(@RequestBody ObjectNode objectNode) {
        String email = objectNode.get("email").asText();
        String password = objectNode.get("password").asText();

        Student s = repository.findById(email).orElseThrow(RuntimeException::new);
        String dbPassword = s.getPassword();
        if (!dbPassword.equals(password)) {
            throw new RuntimeException();
        } else {
            return (s);
        }
    }

    @GetMapping("/{id}/plan")
    public PlanOfStudy plan(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy();
    }

    @PostMapping("/{id}/plan/addcourse")
    public ResponseEntity addCourse(@PathVariable String id, @RequestBody ObjectNode objectNode) throws URISyntaxException {
        int year = Integer.parseInt(objectNode.get("year").asText());
        Semester.Season season = Semester.Season.valueOf(objectNode.get("season").asText());
        String courseIdDepartment = objectNode.get("courseIdDepartment").asText();
        String courseIdNumber = objectNode.get("courseIdNumber").asText();
        String courseTitle = objectNode.get("courseTitle").asText();
        String department = objectNode.get("department").asText();
        String college = objectNode.get("college").asText();
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        Semester semester = student.getPlanOfStudy().getSemesterByDate(season, year);
        semester.addCourse(new Course(courseIdDepartment, courseIdNumber, courseTitle, department, college));
        repository.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/plan/courses")
    public ArrayList<Course> coursesTaken(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy().getCoursesTaken();
    }
    @GetMapping("/{id}/plan/courses/suggested")
    public ArrayList<Course> SuggestedCourses(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        ArrayList<Course> coursesTaken = s.getPlanOfStudy().getCoursesTaken();
        DegreeGraph graph = degreeGraphController.getDegree("CS");
        ArrayList<String> suggestedNames = graph.getNextEligibleClassesController(coursesTaken);
        ArrayList<Course> coursesSuggested = new ArrayList<>();
        for (String name : suggestedNames) {
            coursesSuggested.add(courseRepository.findCourseByCourseID(name));
        }
        return coursesSuggested;
    }


    @GetMapping("/{id}/plan/requirements")
    public ArrayList<Requirement> requirementsLeft(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy().requirementsLeft();
    }



    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) throws URISyntaxException {
        Student savedStudent = repository.save(student);
        return ResponseEntity.created(new URI("/students/" + savedStudent.getEmail())).body(savedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
