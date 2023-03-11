package com.boileradvsr.backend.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


public class Review {
    Student studentReviewer;
    Professor professor;
    double overallRating;
    String reviewText;
    //Date dateOfReview;

    public Review(Student studentReviewer, Professor professor, double overallRating) {
        this.studentReviewer = studentReviewer;
        this.professor = professor;
        this.overallRating = overallRating;

    }

    public Review(Student studentReviewer, String reviewText) {
        this.studentReviewer = studentReviewer;
        this.reviewText = reviewText;
    }

    public Review(Student studentReviewer, double overallRating) {
        this.studentReviewer = studentReviewer;
        this.overallRating = overallRating;
    }




    //TODO need to add student equals;
    public boolean equals(Review comparison) {
        if (!reviewText.equals(comparison.getReviewText())) return false;
        if (!(overallRating == comparison.getOverallRating())) return false;
        
        return true;
    }


    public String getReviewText() {
        return reviewText;
    }
    public double getOverallRating() {
        return overallRating;
    }

    public Student getStudentReviewer() {
        return studentReviewer;
    }
}