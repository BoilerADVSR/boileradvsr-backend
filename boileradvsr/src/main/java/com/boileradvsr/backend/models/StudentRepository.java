package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByFirstName(String firstName);
}
