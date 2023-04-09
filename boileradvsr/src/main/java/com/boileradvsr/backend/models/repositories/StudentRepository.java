package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByFirstName(String firstName);
    Student findStudentByEmail(String email);
    List<Student> findAll();

}
