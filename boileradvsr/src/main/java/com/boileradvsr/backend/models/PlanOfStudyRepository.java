package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanOfStudyRepository extends MongoRepository<PlanOfStudy, String> {
}
