package com.boileradvsr.backend.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reviews")
public class Review {
    String studentReviewer;
    String professor;
    String course;
    double overallRating;
    String reviewText;
    //Date dateOfReview;

    public Review(String studentReviewer, String course, String professor, double overallRating) {
        this.studentReviewer = studentReviewer;
        this.professor = professor;
        this.overallRating = overallRating;

    }

    public Review(String studentReviewer, String course, String reviewText) {
        this.studentReviewer = studentReviewer;
        this.reviewText = reviewText;
        this.course = course;
        overallRating = -1;
    }

    public Review(String studentReviewer, String course, double overallRating) {
        this.course = course;
        this.studentReviewer = studentReviewer;
        this.overallRating = overallRating;
    }

    public Review() {};




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

    public String getProfessor() {
        return professor;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setStudentReviewer(String studentReviewer) {
        this.studentReviewer = studentReviewer;
    }

    public String getStudentReviewer() {
        return studentReviewer;
    }

}