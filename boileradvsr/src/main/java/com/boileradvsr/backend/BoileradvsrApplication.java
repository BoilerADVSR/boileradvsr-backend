package com.boileradvsr.backend;

import com.boileradvsr.backend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EnableMongoRepositories

public class BoileradvsrApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DegreeGraphRepository degreeGraphRepository;


    public static void main(String[] args) {
        SpringApplication.run(BoileradvsrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        degreeRepository.deleteAll();
        professorRepository.deleteAll();
        studentRepository.save(new Student("Brandon", "hart", "b@purdue.edu", "pw"));
        Degree compsci = new Degree(Degree.DEGREETYPE.MAJOR, "Computer Science", "Science", "Computer Science");
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Data Science", "Science", "Computer Science"));
        degreeRepository.save(new Degree(Degree.DEGREETYPE.MAJOR, "Mathematics", "Science", "Math"));

        Degree csML = new Degree(Degree.DEGREETYPE.CONCENTRATION, "Machine Learning", "Science", "Computer Science");
        Degree csSWE = new Degree(Degree.DEGREETYPE.CONCENTRATION, "Software Engineering", "Science", "Computer Science");


        Course cs180 = new Course("CS", "180", "Object-Oriented Programming", "Computer Science", "Science");
        Course cs182 = new Course("CS", "182", "Discrete Mathematics", "Computer Science", "Science");
        Course cs240 = new Course("CS", "240", "C Programming", "Computer Science", "Science");
        Course cs250 = new Course("CS", "250", "Computer Architecture", "Computer Science", "Science");
        Course cs251 = new Course("CS", "251", "Data Structures and Algorithms", "Computer Science", "Science");
        Course cs252 = new Course("CS", "252", "Systems Programming", "Computer Science", "Science");
        Course cs307 = new Course("CS", "307", "Software Engineering", "Computer Science", "Science");
        Course cs352 = new Course("CS", "352", "Compilers", "Computer Science", "Science");
        Course cs354 = new Course("CS", "354", "Operating Systems", "Computer Science", "Science");
        Course cs381 = new Course("CS", "381", "Intro Analysis Algor", "Computer Science", "Science");
        Course cs373 = new Course("CS", "373", "Data Mining and Machine Learning", "Computer Science", "Science");

        Course ma261 = new Course("MA", "261", "Multivariate Calculus", "Math", "Science");
        Course ma265 = new Course("MA", "265", "Linear Algebra", "Math", "Science");
        Course ma351 = new Course("MA", "351", "Elementary Linear Algebra", "Math", "Science");


        Requirement csLinearAlgebra = new Requirement("Linear Algebra",Requirement.Type.CORE);
        csLinearAlgebra.addCourse(ma265);
        csLinearAlgebra.addCourse(ma351);
        Requirement csMultivariate = new Requirement("Multivariate Calculus",Requirement.Type.CORE, ma261);

        compsci.addRequirement(new Requirement("Software Engineering",Requirement.Type.CORE, cs307));
        compsci.addRequirement(new Requirement("Discrete Mathematics",Requirement.Type.CORE, cs182));
        compsci.addRequirement(new Requirement("Object Oriented",Requirement.Type.CORE, cs180));
        compsci.addRequirement(new Requirement("Data Structures and Algorithms",Requirement.Type.CORE, cs251));
        compsci.addRequirement(new Requirement("Systems Programming",Requirement.Type.CORE, cs252));
        compsci.addRequirement(new Requirement("Computer Architecture", Requirement.Type.CORE,cs250));

        compsci.addRequirement(csLinearAlgebra);
        compsci.addRequirement(csMultivariate);
        courseRepository.save(cs240);
        courseRepository.save(cs180);
        courseRepository.save(cs182);
        courseRepository.save(cs250);
        courseRepository.save(cs251);
        courseRepository.save(cs252);
        courseRepository.save(cs307);
        courseRepository.save(cs373);
        courseRepository.save(cs381);
        courseRepository.save(cs352);
        courseRepository.save(cs354);
        courseRepository.save(ma351);
        courseRepository.save(ma261);
        courseRepository.save(ma265);
        courseRepository.save(ma351);
        degreeRepository.save(compsci);

        ArrayList<Course> oscmp = new ArrayList<>();
        oscmp.add(cs352);
        oscmp.add(cs354);

        csSWE.addRequirement(new Requirement("Software Engineering", Requirement.Type.CORE,cs307));
        csSWE.addRequirement(new Requirement("Operating Systems or Compilers", Requirement.Type.CORE, oscmp));
        csSWE.addRequirement(new Requirement("Analysis of Algorithms", Requirement.Type.CORE, cs381));
        csML.addRequirement(new Requirement("Compilers", Requirement.Type.CORE, cs352));
        csML.addRequirement(new Requirement("Analysis of Algorithms",Requirement.Type.CORE, cs381));
        csML.addRequirement(new Requirement("Data Mining and ML",Requirement.Type.CORE, cs373));


        Student g = new Student("Garrett", "O'Brien", "g@purdue.edu", "pw");
        PlanOfStudy gpos = new PlanOfStudy();
        Semester s2023 = new Semester(2023, Semester.Season.SPRING);
        gpos.addSemester(s2023);
        gpos.addDegree(compsci);
        gpos.addDegree(csML);
        gpos.addDegree(csSWE);
        g.setPlanOfStudy(gpos);
        studentRepository.save(g);

        Professor turkstra = new Professor("Jeff", "Turkstra", "Computer Science");

        turkstra.addCourse(cs307);
        professorRepository.save(turkstra);

        ArrayList<String> classes = new ArrayList<>();
        classes.add("CS180");
        classes.add("CS182");
        classes.add("CS240");
        classes.add("CS250");
        classes.add("CS251");
        classes.add("CS252");
        classes.add("CS307");
        classes.add("CS373");
        classes.add("CS381");
        classes.add("CS352");
        classes.add("CS354");


        DegreeGraph graph = new DegreeGraph("CS",11, classes);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(4,7);
        graph.addEdge(4,8);
        graph.addEdge(5,9);
        graph.addEdge(5, 10);
        degreeGraphRepository.save(graph);





        System.out.println("running");


    }

}
