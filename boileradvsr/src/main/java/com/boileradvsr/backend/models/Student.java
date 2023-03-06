package com.boileradvsr.backend.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Document(collection = "students")
public class Student {
    String firstName;
    String lastName;
    @Id
    String email;
    String password;
    Semester graduationSemester;
    double GPA;
    ArrayList<Semester> semesters;
    ArrayList<Degree> degrees;
    PlanOfStudy planOfStudy;
    ArrayList<Advisor> academicAdvisors;
    ArrayList<Review> reviews;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        semesters = new ArrayList<>();
        academicAdvisors = new ArrayList<>();
        degrees = new ArrayList<>();
        reviews = new ArrayList<>();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Semester getGraduationSemester() {
        return graduationSemester;
    }

    public void setGraduationSemester(Semester graduationSemester) {
        this.graduationSemester = graduationSemester;
    }

    public double getGPA() {
        return GPA;
    }

    public void setSemesters(ArrayList<Semester> semesters) {
        this.semesters = semesters;
    }

    public ArrayList<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(ArrayList<Degree> degrees) {
        this.degrees = degrees;
    }

    public PlanOfStudy getPlanOfStudy() {
        return planOfStudy;
    }

    public void setPlanOfStudy(PlanOfStudy planOfStudy) {
        this.planOfStudy = planOfStudy;
    }

    public ArrayList<Advisor> getAcademicAdvisors() {
        return academicAdvisors;
    }

    public void setAcademicAdvisors(ArrayList<Advisor> academicAdvisors) {
        this.academicAdvisors = academicAdvisors;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", graduationSemester=" + graduationSemester +
                ", GPA=" + GPA +
                ", semesters=" + semesters +
                ", degrees=" + degrees +
                ", planOfStudy=" + planOfStudy +
                ", academicAdvisors=" + academicAdvisors +
                ", reviews=" + reviews +
                '}';
    }
}