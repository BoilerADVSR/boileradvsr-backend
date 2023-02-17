package com.backend.boileradvsr.boileradvsr;
import java.util.ArrayList;

public class Course {
    private String courseIdDepartment;
    private String courseIdNumber;
    private String courseTitle;
    private String department;
    private String college;
    private double averageRating;
    private double averageGPA;
    private ArrayList<Grade> gradePercentages;
    private ArrayList<Review> reviews;
    


    public Course(String courseIdDepartment, String courseIdNumber, String courseTitle, String department, String college) {
        this.courseIdDepartment = courseIdDepartment;
        this.courseIdNumber = courseIdNumber;
        this.courseTitle = courseTitle;
        this.department = department;
        this.college = college;
    }


}