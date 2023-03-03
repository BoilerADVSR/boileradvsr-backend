package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    public Course findByCourseTitle(String courseTitle);
    public Course findCourseByCourseID(String courseID);
    public List<Course> findCoursesByAverageGPAGreaterThanEqual(double averageGPA);
    public List<Course> findCoursesByCourseIdDepartment(String department);
    public List<Course> findCoursesByAverageGPAGreaterThanEqualOrderByAverageGPADesc(double averageGPA);
    public List<Course> findCoursesByAverageRatingOrderByAverageRatingDesc(double rating);
}
