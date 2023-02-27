package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DegreeRepository extends MongoRepository<Degree, String> {
    Degree findDegreeByDegreeTitle(String degreeTitle);
    List<Degree> findDegreesByCollege(String college);
    List<Degree> findDegreesByDepartment(String department);
    List<Degree> findDegreesByDegreeType(Degree.DEGREETYPE degreetype);
}
