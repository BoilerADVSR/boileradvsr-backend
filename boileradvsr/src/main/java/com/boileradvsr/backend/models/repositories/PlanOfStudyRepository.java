package com.boileradvsr.backend.models.repositories;

import com.boileradvsr.backend.models.PlanOfStudy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanOfStudyRepository extends MongoRepository<PlanOfStudy, String> {
    @Override
    Optional<PlanOfStudy> findById(String s);
}
