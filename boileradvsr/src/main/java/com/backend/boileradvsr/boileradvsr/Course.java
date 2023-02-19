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
    private Grade grade;
    private ArrayList<Review> reviews;

    enum COURSETYPE {
        STUDENT,
        DATABASE
    }    


    public Course(String courseIdDepartment, String courseIdNumber, String courseTitle, String department, String college, COURSETYPE type) {
        this.courseIdDepartment = courseIdDepartment;
        this.courseIdNumber = courseIdNumber;
        this.courseTitle = courseTitle;
        this.department = department;
        this.college = college;

        if (type == COURSETYPE.DATABASE) {
            reviews = new ArrayList<Review>();
        }
    }

    //Database course constructer
    public Course() {}


    //needs implementation
    public boolean equals (Course comparison) {
        if (!courseIdDepartment.equals(comparison.getCourseIdDepartment())) return false;
        if (!courseIdNumber.equals(comparison.getCourseIdNumber())) return false;
        if (!courseTitle.equals(comparison.getCourseTitle())) return false;
        if (!college.equals(comparison.getCollege())) return false;
        if (!department.equals(comparison.getDepartment())) return false;

        return true;
    }


    public void addReview(Review review) {
        reviews.add(review);
    }

    public boolean deleteReview(Review review) {
        for (int index = 0; index < reviews.size(); index++) {
            if (review.equals(reviews.get(index))) {
                reviews.remove(index);
                return true;
            }
        }
        return false;
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

    public Grade getGrade() {
        return grade;
    }
    
    public double getAverageGPA() {
        return averageGPA;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


}