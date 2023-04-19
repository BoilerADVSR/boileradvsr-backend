package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.CourseRepository;
import com.boileradvsr.backend.models.repositories.StudentRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    StudentRepository studentRepository;

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
    @PostMapping("/database/create")
    public ResponseEntity<Course> createCourse(@RequestBody String courseInfo) {
        String[] info = courseInfo.split("><");
        String courseIdDepartment = info[0];
        String courseIdNumber = info[1];
        String courseTitle = info[2];
        String department = info[3];
        String college = info[4];
        int creditHours =(int) Double.parseDouble(info[5]);
        Course course = new Course(courseIdDepartment,courseIdNumber,courseTitle,department,college,creditHours);
        repository.save(course);
        return ResponseEntity.ok(course);

    }

    @PutMapping("/{id}/addreview")
    public ResponseEntity<Course> addReview(@PathVariable String id, @RequestBody ObjectNode objectNode) throws URISyntaxException {
        String studentId = objectNode.get("studentID").asText();
        String reviewText = objectNode.get("reviewText").asText();
        double rating = Double.parseDouble(objectNode.get("rating").asText());
        String courseID = objectNode.get("courseID").asText();

        Student student = studentRepository.findById(studentId).orElseThrow(RuntimeException::new);
        Course course = repository.findById(id).orElseThrow(RuntimeException::new);
        Review review = new Review(student.getFirstName() + " " + student.getLastName(), courseID, reviewText, rating);
        course.addReview(review);
        student.addReview(review);
        repository.save(course);
        studentRepository.save(student);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}/addquestion")
    public ResponseEntity<Course> addQuestion(@PathVariable String id, @RequestBody ObjectNode objectNode) throws URISyntaxException {
        String studentId = objectNode.get("studentID").asText();
        String questionText = objectNode.get("question").asText();
        Question.discussionType type = Question.discussionType.valueOf(objectNode.get("type").asText().toUpperCase());
        String discussionID = objectNode.get("discussion").asText();
        Course course = repository.findById(id).orElseThrow(RuntimeException::new);
        if (type.equals(Question.discussionType.QUESTION)) {
            course.getDiscussion().add(new Question(studentId, questionText, type));
        } else {
            Student student = studentRepository.findById(studentId).orElseThrow(RuntimeException::new);
            if (!student.getPlanOfStudy().listCourseIDsTaken().contains(id)) {
                return (new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
            }
            for (Question q : course.getDiscussion()) {
                if (q.getId().equals(discussionID)) {
                    q.getResponses().add(new Question(studentId, questionText, type));
                }
            }
        }
        repository.save(course);
        return ResponseEntity.ok(course);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody Course course) {
        Course currentCourse = repository.findCourseByCourseID(id);
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
