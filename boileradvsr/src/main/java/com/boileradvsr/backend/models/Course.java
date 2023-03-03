package com.boileradvsr.backend.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "courseCatalog")
public class Course {
    @Id
    public String courseID;
    private String courseIdDepartment;
    private String courseIdNumber;
    private String courseTitle;
    private String department;
    private String college;
    private ArrayList<Integer> ratings;
    private double averageRating;
    private double averageGPA;
    private Grade grade;
    private ArrayList<Review> reviews;

    enum COURSETYPE {
        STUDENT,
        DATABASE
    }    


    public Course(String courseIdDepartment, String courseIdNumber, String courseTitle, String department, String college) {
        this.courseID = courseIdDepartment + courseIdNumber;
        this.courseIdDepartment = courseIdDepartment;
        this.courseIdNumber = courseIdNumber;
        this.courseTitle = courseTitle;
        this.department = department;
        this.college = college;
        ratings = new ArrayList<Integer>();
        //TODO needs fixing (set a default rating)
        averageRating = 5;

    }

    //TODO needs to be fixed *maybe(currently bad)
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

    public String getCourseID() {
        return courseID;
    }

    public void setAverageGPA(double averageGPA) {
        this.averageGPA = averageGPA;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setCourseIdNumber(String courseIdNumber) {
        this.courseIdNumber = courseIdNumber;
    }

    public void setCourseIdDepartment(String courseIdDepartment) {
        this.courseIdDepartment = courseIdDepartment;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseIdDepartment='" + courseIdDepartment + '\'' +
                ", courseIdNumber='" + courseIdNumber + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", department='" + department + '\'' +
                ", college='" + college + '\'' +
                ", ratings=" + ratings +
                ", averageRating=" + averageRating +
                ", averageGPA=" + averageGPA +
                ", grade=" + grade +
                ", reviews=" + reviews +
                '}';
    }
}