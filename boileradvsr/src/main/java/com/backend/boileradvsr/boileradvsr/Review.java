package com.backend.boileradvsr.boileradvsr;
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

}