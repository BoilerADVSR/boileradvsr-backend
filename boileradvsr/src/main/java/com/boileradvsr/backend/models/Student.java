package com.boileradvsr.backend.models;
import java.util.ArrayList;

public class Student {
    String firstName;
    String lastName;
    String email;
    String password;
    Semester graduationSemester;
    double GPA;
    ArrayList<Semester> semesters;
    ArrayList<Degree> degrees;
    PlanOfStudy planOfStudy;
    ArrayList<Advisor> academicAdvisors;

    public Student(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public ArrayList<Semester> getSemesters() {
        return(semesters);
    }

    public void addSemester(Semester semester) {
        semesters.add(semester);
    }

    public void addDegree(Degree degree) {
        degrees.add(degree);
    }

    
}