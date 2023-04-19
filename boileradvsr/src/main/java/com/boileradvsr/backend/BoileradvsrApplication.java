package com.boileradvsr.backend;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.*;
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
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private EventRepository eventRepository;


    public static void main(String[] args) {
        SpringApplication.run(BoileradvsrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("running");
    }

}
