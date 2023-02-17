package com.backend.boileradvsr.boileradvsr;
import java.util.ArrayList;

public class Student {
    String firstName;
    String lastName;
    String email;
    String password;
    Semester graduationSemester;
    double GPA;
    ArrayList<Course> courses;
    ArrayList<Degree> degrees;
    PlanOfStudy planOfStudy;
    ArrayList<Advisor> academicAdvisors;

    public Student(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}