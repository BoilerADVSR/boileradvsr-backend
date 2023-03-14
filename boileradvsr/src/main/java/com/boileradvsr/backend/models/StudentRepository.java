package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByFirstName(String firstName);
    Optional<Student> findByEmail(String email);
    Student findStudentByEmail(String email);
    List<Student> findAll();

}
