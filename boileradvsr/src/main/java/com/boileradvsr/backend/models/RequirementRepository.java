package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequirementRepository extends MongoRepository<Requirement, String> {
    Requirement findByRequirementId(String id);
}
