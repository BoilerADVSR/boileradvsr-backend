package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByFirstName(String firstName);
    Optional<Student> findById(String id);
    Student findStudentByEmail(String email);
    List<Student> findAll();

}
