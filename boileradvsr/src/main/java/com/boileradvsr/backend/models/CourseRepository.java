package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    public Course findByCourseTitle(String courseTitle);
    public List<Course> findByCourseIdDepartment(String courseIdDepartment);
    public Course findCourseByCourseID(String courseID);
}
