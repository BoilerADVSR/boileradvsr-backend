package com.boileradvsr.backend.controllers;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.ChatRepository;
import com.boileradvsr.backend.models.repositories.CourseRepository;
import com.boileradvsr.backend.models.repositories.DegreeRepository;
import com.boileradvsr.backend.models.repositories.StudentRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    public PassChangeService changeService;
    @Autowired
    public ChatRepository chatRepository;

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

    @PutMapping("/login")
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

    @PostMapping("/{id}/plan/addbacklog")
    public ResponseEntity addBacklog(@PathVariable String id, @RequestBody Course course) throws URISyntaxException {
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        student.getBackLog().add(course);
        repository.save(student);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/{id}/plan/removebacklog/{courseid}")
    public ResponseEntity removeBacklog(@PathVariable String id, @PathVariable String courseid) throws URISyntaxException {
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        student.removeCourseBacklog(courseid);
        repository.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/plan/courses")
    public ArrayList<Course> coursesTaken(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy().listCoursesTaken();
    }
    @GetMapping("/{id}/plan/courses/suggested")
    public ArrayList<Course> SuggestedCourses(@PathVariable String id, @RequestBody ObjectNode objectNode) {
        String degree = objectNode.get("degree").asText();

        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        ArrayList<Course> coursesTaken = s.getPlanOfStudy().listCoursesTaken();
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
        ArrayList<Course> coursesTaken = s.getPlanOfStudy().listCoursesTaken();
        //default sort is avgGPA
        String sort = "N/A";
        int level = -1;
        if (params.containsKey("sort")) sort = params.get("sort");
        if (params.containsKey("level")) level = Integer.parseInt(params.get("level"));

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

        ArrayList<String> courseNames = Suggest.suggestASemester(s, graph, concentrations, eligibleCourses, sort, level);
        ArrayList<Course> courses = new ArrayList<>();
        for (String courseId : courseNames) {
            courses.add(courseRepository.findCourseByCourseID(courseId));
        }


         return courses;
    }

    @GetMapping("/change/pass")
    public String passChange(@RequestBody PassChangeReq request) {
        return changeService.change(request);
    }



    @PutMapping("/change/pass={email}")
    public ResponseEntity<Student> passChangeResp(@RequestBody PassChangeResponse response,
                                                  @PathVariable String email) {
        Student student = repository.findById(email).orElseThrow();
        student.setPassword(response.getPassword());
        repository.save(student);
        return ResponseEntity.ok(student);
    }




    @GetMapping("/{id}/plan/requirements")
    public ArrayList<Requirement> requirementsLeft(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        return s.getPlanOfStudy().requirementsLeft();
    }

    @GetMapping("/{id}/connections")
    public ArrayList<Student> getConnections(@PathVariable String id) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        ArrayList<Student> students = new ArrayList<>();
        for (String studentID : s.getConnectionsIds()) {
            students.add(repository.findById(studentID).orElseThrow(RuntimeException::new));
        }
        return students;
    }


    @PutMapping("/{id}/connection/request/{connectionID}")
    public ResponseEntity requestConnection(@PathVariable String id, @PathVariable String connectionID) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        Student connection = repository.findById(connectionID).orElseThrow(RuntimeException::new);
        connection.getConnectionRequests().add(s.getEmail());
        repository.save(connection);
        return ResponseEntity.ok(s);
    }

    @PutMapping("/{id}/connection/handle/{connectionID}")
    public ResponseEntity handleConnection(@PathVariable String id, @PathVariable String connectionID, @RequestBody ObjectNode objectNode) {
        Student s = repository.findById(id).orElseThrow(RuntimeException::new);
        Student connection = repository.findById(connectionID).orElseThrow(RuntimeException::new);
        String status = objectNode.get("status").asText();
        if (status.equals("accept")) {
            if (s.getConnectionsIds().contains(connection.getEmail())) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            s.getConnectionsIds().add(connection.getEmail());
            connection.getConnectionsIds().add(s.getEmail());
            Chat chat = new Chat(s.getEmail(), connection.getEmail());
            chatRepository.save(chat);
        }
        s.getConnectionRequests().remove(connectionID);
        repository.save(s);
        repository.save(connection);
        return ResponseEntity.ok(s);
    }




    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody Student student) {
        Student updatedStudent = repository.findById(id).orElseThrow(RuntimeException::new);
        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setPassword(student.getPassword());
        updatedStudent.setGraduationSemester(student.getGraduationSemester());
        updatedStudent.setGPA(student.getGPA());
        updatedStudent.setPlanOfStudy(student.getPlanOfStudy());
        updatedStudent.setAcademicAdvisors(student.getAcademicAdvisors());
        updatedStudent.setReviews(student.getReviews());
        updatedStudent.setAboutMe(student.getAboutMe());
        updatedStudent.setLinkedIn(student.getLinkedIn());
        updatedStudent.setBackLog(student.getBackLog());
        updatedStudent.setConnectionsIds(student.getConnectionsIds());
        updatedStudent.setNotifications(student.getNotifications());
        updatedStudent = repository.save(student);
        return ResponseEntity.ok(updatedStudent);
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
