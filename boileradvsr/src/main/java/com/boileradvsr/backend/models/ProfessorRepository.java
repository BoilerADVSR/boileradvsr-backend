package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
}
