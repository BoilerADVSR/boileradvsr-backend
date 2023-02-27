package com.boileradvsr.backend.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    public Course findByCourseTitle(String courseTitle);
<<<<<<< HEAD
    public Course findCourseByCourseID(String courseID);
    public List<Course> findCoursesByAverageGPAGreaterThanEqual(double averageGPA);
    public List<Course> findByCourseIdDepartment(String courseIdDepartment);

=======
    public List<Course> findByCourseIdDepartment(String courseIdDepartment);
    public Course findCourseByCourseID(String courseID);
>>>>>>> 3bd63da (Controller classes for query)
}
