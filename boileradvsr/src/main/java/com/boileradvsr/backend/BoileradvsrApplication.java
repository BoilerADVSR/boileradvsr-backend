package com.boileradvsr.backend;

import com.boileradvsr.backend.models.Course;
import com.boileradvsr.backend.models.CourseRepository;
import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
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


    public static void main(String[] args) {
        SpringApplication.run(BoileradvsrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        studentRepository.save(new Student("Garrett", "O'Brien", "g@purdue.edu", "pw"));
        studentRepository.save(new Student("Brandon", "hart", "b@purdue.edu", "pw"));

        courseRepository.save(new Course("CS", "307", "Software Engineering", "Computer Science", "Science"));
        courseRepository.save(new Course("CS", "182", "Discrete Mathematics", "Computer Science", "Science"));
        courseRepository.save(new Course("MA", "341", "Foundations of Analysis", "Math", "Science"));

        for (Course course : courseRepository.findByCourseIdDepartment("CS")) {
            System.out.println(course.getCourseID());
        }

        System.out.println(courseRepository.findCourseByCourseID("MA341").getDepartment());


    }

}
