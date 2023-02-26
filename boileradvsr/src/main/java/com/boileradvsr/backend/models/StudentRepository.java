package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{ 'email': '?0' }")
    public Student findByEmail(String email);
    @Query("{ 'password': '?0' }")
    public Student findByPassword(String password);
}
