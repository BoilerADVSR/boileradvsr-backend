package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
}
