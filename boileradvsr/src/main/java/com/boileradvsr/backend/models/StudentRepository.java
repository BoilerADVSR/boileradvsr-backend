package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    public Student findByFirstName(String firstName);
}
