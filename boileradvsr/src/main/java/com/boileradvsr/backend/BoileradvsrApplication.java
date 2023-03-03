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
        Degree compsci = new Degree(Degree.DEGREETYPE.MAJOR, "Computer Science", "Science", "Computer Science");
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Data Science", "Science", "Computer Science"));
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Mathematics", "Science", "Math"));

        Course cs180 = new Course("CS", "18000", "Object-Oriented Programming", "Computer Science", "Science");
        Course cs182 = new Course("CS", "18200", "Discrete Mathematics", "Computer Science", "Science");
        Course cs250 = new Course("CS", "25000", "Computer Architecture", "Computer Science", "Science");
        Course cs251 = new Course("CS", "25100", "Data Structures and Algorithms", "Computer Science", "Science");
        Course cs252 = new Course("CS", "25200", "Systems Programming", "Computer Science", "Science");
        Course cs307 = new Course("CS", "30700", "Software Engineering", "Computer Science", "Science");
        Course cs352 = new Course("CS", "35200", "Compilers", "Computer Science", "Science");
        Course cs354 = new Course("CS", "35400", "Operating Systems", "Computer Science", "Science");
        Course cs381 = new Course("CS", "38100", "Intro Analysis Algor", "Computer Science", "Science");
        Course ma261 = new Course("MA", "26100", "Multivariate Calculus", "Math", "Science");
        Course ma265 = new Course("MA", "26500", "Linear Algebra", "Math", "Science");
        Course ma351 = new Course("MA", "35100", "Elementary Linear Algebra", "Math", "Science");


        Requirement csLinearAlgebra = new Requirement("Linear Algebra");
        csLinearAlgebra.addCourse(ma265);
        csLinearAlgebra.addCourse(ma351);
        Requirement csMultivariate = new Requirement("Multivariate Calculus", ma261);
        compsci.addRequirement(csLinearAlgebra);
        compsci.addRequirement(csMultivariate);

        courseRepository.save(cs180);
        courseRepository.save(cs182);
        courseRepository.save(cs250);
        courseRepository.save(cs251);
        courseRepository.save(cs252);
        courseRepository.save(cs307);
        courseRepository.save(ma351);
        degreeRepository.save(compsci);



        /*for (Course course : courseRepository.findCoursesByCourseIdDepartment("CS")) {
            System.out.println(course.getCourseID());
        }*/

        //System.out.println(courseRepository.findCourseByCourseID("MA351").getDepartment());

        System.out.println("running");


    }

}
