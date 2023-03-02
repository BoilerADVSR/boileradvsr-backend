package com.boileradvsr.backend;

import com.boileradvsr.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories

public class BoileradvsrApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DegreeRepository degreeRepository;


    public static void main(String[] args) {
        SpringApplication.run(BoileradvsrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        degreeRepository.deleteAll();
        studentRepository.save(new Student("Garrett", "O'Brien", "g@purdue.edu", "pw"));
        studentRepository.save(new Student("Brandon", "hart", "b@purdue.edu", "pw"));
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Computer Science", "Science", "Computer Science"));
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Data Science", "Science", "Computer Science"));
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Mathematics", "Science", "Math"));

        Course cs180 = new Course("CS", "180", "Object-Oriented Programming", "Computer Science", "Science");
        Course cs250 = new Course("CS", "250", "Computer Architecture", "Computer Science", "Science");
        Course cs252 = new Course("CS", "252", "Systems Programming", "Computer Science", "Science");
        Course cs182 = new Course("CS", "182", "Discrete Mathematics", "Computer Science", "Science");
        Course cs307 = new Course("CS", "307", "Software Engineering", "Computer Science", "Science");
        Course ma341 = new Course("MA", "341", "Foundations of Analysis", "Math", "Science");

        cs180.setAverageGPA(3.8);
        cs250.setAverageGPA(2.9);
        cs182.setAverageGPA(3.1);
        cs307.setAverageGPA(3.3);
        cs252.setAverageGPA(2.9);
        ma341.setAverageGPA(3.0);

        courseRepository.save(cs180);
        courseRepository.save(cs182);
        courseRepository.save(cs250);
        courseRepository.save(cs252);
        courseRepository.save(cs307);
        courseRepository.save(ma341);



        for (Course course : courseRepository.findByCourseIdDepartment("CS")) {
            System.out.println(course.getCourseID());
        }

        System.out.println(courseRepository.findCourseByCourseID("MA341").getDepartment());


    }

}
