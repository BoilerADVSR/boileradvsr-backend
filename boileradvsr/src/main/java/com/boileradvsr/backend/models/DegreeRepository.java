package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DegreeRepository extends MongoRepository<Degree, String> {
    public Degree findDegreeByDegreeTitle(String degreeTitle);
    public List<Degree> findDegreesByCollege(String college);
    public List<Degree> findDegreesByDepartment(String department);
    public List<Degree> findDegreesByDegreeType(Degree.DEGREETYPE degreetype);
    public List<Degree> findDegreesByDepartmentAndDegreeType(String department, Degree.DEGREETYPE degreetype);
}
