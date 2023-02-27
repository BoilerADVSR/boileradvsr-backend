package com.boileradvsr.backend.models;
import java.util.Date;


public class Review {
    Course course;
    Student studentReviewer;
    Professor professor;
    double overallRating;
    String reviewText;
    Date dateOfReview;

    public Review(Course course, Student studentReviewer, Professor professor, double overallRating) {
        this.course = course;
        this.studentReviewer = studentReviewer;
        this.professor = professor;
        this.overallRating = overallRating;

    }

    public Review(Course course, Student studentReviewer, Professor professor, String reviewText) {
        this.course = course;
        this.studentReviewer = studentReviewer;
        this.professor = professor;
        this.reviewText = reviewText;


    }

    //TODO need to add student equals;
    public boolean equals(Review comparison) {
        if (!course.equals(comparison.getCourse())) return false;
        if (!reviewText.equals(comparison.getReviewText())) return false;
        if (!(overallRating == comparison.getOverallRating())) return false;
        
        return true;
    }

    public Course getCourse() {
        return course;
    }

    public String getReviewText() {
        return reviewText;
    }
    public double getOverallRating() {
        return overallRating;
    }

}