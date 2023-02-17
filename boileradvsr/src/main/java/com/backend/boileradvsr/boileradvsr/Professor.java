package com.backend.boileradvsr.boileradvsr;

import java.util.ArrayList;

public class Professor {
    String firstName;
    String lastName;
    String department;
    ArrayList<Course> coursesTaught;

    public Professor(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
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

}