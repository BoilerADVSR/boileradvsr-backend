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

    //needs implementation
    public boolean equals (Course comparison) {
        if (!courseIdDepartment.equals(comparison.getCourseIdDepartment())) return false;
        if (!courseIdNumber.equals(comparison.getCourseIdNumber())) return false;
        if (!courseTitle.equals(comparison.getCourseTitle())) return false;
        if (!college.equals(comparison.getCollege())) return false;
        if (!department.equals(comparison.getDepartment())) return false;

        return true;
    }

    public String getCourseIdDepartment() {
        return courseIdDepartment;
    }

    public String getCourseIdNumber() {
        return courseIdNumber;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public String getCollege() {
        return college;
    }
    public String getDepartment() {
        return department;
    }


}