package com.boileradvsr.backend.models;

import java.util.ArrayList;

public class Professor {
    String firstName;
    String lastName;
    String department;
    ArrayList<Course> coursesTaught;
    String rateMyProfessor;
    //https://github.com/tisuela/ratemyprof-api needs Implementation

    public Professor(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        coursesTaught = new ArrayList<Course>();
    }

    public Professor(String firstName, String lastName, String department, ArrayList<Course> coursesTaught) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.coursesTaught = coursesTaught;
    }

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

}