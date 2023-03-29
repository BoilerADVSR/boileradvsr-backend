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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableMongoRepositories
@RequestMapping("/students")
public class StudentController {
    @Autowired
    public DegreeGraphController degreeGraphController;

    @Autowired
    public CourseRepository courseRepository;
    @Autowired
    public DegreeRepository degreeRepository;

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

    @GetMapping("/{id}/plan/semesters")
    public ArrayList<Semester> semesters(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy().getSemesters();
    }
    @PostMapping("/{id}/plan/adddegree")
    public ResponseEntity addDegree(@PathVariable String id, @RequestBody ObjectNode objectNode)  {
        String degreeTitle = objectNode.get("degree").asText();
        //add = true, add degree, else remove
        boolean add = Boolean.valueOf(objectNode.get("operation").asText());
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);

        if (add) {
            Degree degree = degreeRepository.findById(degreeTitle).orElseThrow(RuntimeException::new);
            student.getPlanOfStudy().addDegree(degree);
        } else {
            student.getPlanOfStudy().removeDegree(degreeTitle);
        }
        repository.save(student);
        return ResponseEntity.ok(student);
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
        int creditHours = Integer.parseInt(objectNode.get("creditHours").asText());
        double grade = Double.parseDouble(objectNode.get("grade").asText());

        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        Semester semester = student.getPlanOfStudy().getSemesterByDate(season, year);
        semester.addCourse(new Course(courseIdDepartment, courseIdNumber, courseTitle, department, college, creditHours, grade));
        student.getPlanOfStudy().calculateGPA();
        repository.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/plan/courses")
    public ArrayList<Course> coursesTaken(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy().getCoursesTaken();
    }
    @GetMapping("/{id}/plan/courses/suggested")
    public ArrayList<Course> SuggestedCourses(@PathVariable String id, @RequestBody ObjectNode objectNode) {
        String degree = objectNode.get("degree").asText();

        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        ArrayList<Course> coursesTaken = s.getPlanOfStudy().getCoursesTaken();
        ArrayList<Degree> concentrations = new ArrayList<>();
        for (Degree concentration : s.getPlanOfStudy().getDegrees()) {
            if (concentration.getDegreeType() == Degree.DEGREETYPE.CONCENTRATION) concentrations.add(concentration);
        }
        //TODO fix for all degrees(add a request body)
        DegreeGraph graph = degreeGraphController.getDegree(degree);
        ArrayList<String> suggestedNames = graph.getNextEligibleClassesController(coursesTaken, concentrations);
        ArrayList<Course> coursesSuggested = new ArrayList<>();
        for (String name : suggestedNames) {
            coursesSuggested.add(courseRepository.findCourseByCourseID(name));
        }
        return coursesSuggested;
    }

    @GetMapping("/{id}/plan/courses/suggestedSemester")
    public ArrayList<Course> suggestedSemester(@PathVariable String id, @RequestParam Map<String, String> params) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        ArrayList<Course> coursesTaken = s.getPlanOfStudy().getCoursesTaken();
        //default sort is avgGPA
        String sort = "N/A";
        if (params.containsKey("sort")) sort = params.get("sort");

        ArrayList<Course> eligibleCourses = new ArrayList<>();
        DegreeGraph graph = degreeGraphController.getDegree("CS");
        ArrayList<Requirement> requirementsLeft = s.getPlanOfStudy().requirementsLeft();
        ArrayList<String> studentCourses = new ArrayList<>();

        for (Requirement requirement : requirementsLeft) {
            if (requirement.getType() == Requirement.Type.ELECTIVE) {
                for (Course course : requirement.getCourses()) {
                    studentCourses.add(course.getCourseID());
                }
            }
        }
        for (String courseId : studentCourses) {
            eligibleCourses.add(courseRepository.findCourseByCourseID(courseId));
        }

        ArrayList<Degree> concentrations = new ArrayList<>();
        for (Degree concentration : s.getPlanOfStudy().getDegrees()) {
            if (concentration.getDegreeType() == Degree.DEGREETYPE.CONCENTRATION) concentrations.add(concentration);
        }

        ArrayList<String> courseNames = Suggest.suggestASemester(s, graph, concentrations, eligibleCourses, sort);
        ArrayList<Course> courses = new ArrayList<>();
        for (String courseId : courseNames) {
            courses.add(courseRepository.findCourseByCourseID(courseId));
        }


         return courses;
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
