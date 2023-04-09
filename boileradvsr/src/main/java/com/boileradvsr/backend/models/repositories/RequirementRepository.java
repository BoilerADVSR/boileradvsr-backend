package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.Requirement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequirementRepository extends MongoRepository<Requirement, String> {
    public Requirement findRequirementByName(String requirementName);
    public List<Requirement> findAll();
}
