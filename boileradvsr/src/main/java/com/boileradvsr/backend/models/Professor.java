package com.boileradvsr.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="professors")
public class Professor {
    @Id
    String id;
    String firstName;
    String lastName;
    String department;
    ArrayList<Course> coursesTaught;
    double rating;
    double avgGPA;

    //https://github.com/tisuela/ratemyprof-api needs Implementation for rate my professor

    public Professor(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        coursesTaught = new ArrayList<>();
        id = firstName + "-" + lastName;

    }

    public Professor(String firstName, String lastName, String department, ArrayList<Course> coursesTaught) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.coursesTaught = coursesTaught;
        id = firstName + "-" + lastName;

    }

    public Professor() {}

    public void addCourse(Course course) {
        coursesTaught.add(course);
    }

    public boolean removeCourse(Course course) {
        for (int index = 0; index < coursesTaught.size(); index++) {
            if (course.equals(coursesTaught.get(index))) {
                coursesTaught.remove(index);
                return true;
            }
        }
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public double getAvgGPA() {
        return avgGPA;
    }

    public double getRating() {
        return rating;
    }

    public String getLastName() {
        return lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAvgGPA(double avgGPA) {
        this.avgGPA = avgGPA;
    }

    public void setCoursesTaught(ArrayList<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}