package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.DegreeGraph;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DegreeGraphRepository extends MongoRepository<DegreeGraph, String> {
}
