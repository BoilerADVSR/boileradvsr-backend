package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByFirstName(String firstName);
    Student findStudentByEmail(String email);
    List<Student> findAll();

}
