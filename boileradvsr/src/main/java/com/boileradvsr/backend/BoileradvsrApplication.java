package com.boileradvsr.backend;

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
    private StudentRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(BoileradvsrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        repository.save(new Student("Garrett", "O'Brien", "g@purdue.edu", "pw"));

        repository.findByFirstName("Garrett");
    }

}
