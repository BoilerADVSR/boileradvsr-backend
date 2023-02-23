package com.boileradvsr.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories

public class BoileradvsrApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoileradvsrApplication.class, args);
    }

}
