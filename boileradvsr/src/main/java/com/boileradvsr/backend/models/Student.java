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
    PlanOfStudy planOfStudy;
    ArrayList<Advisor> academicAdvisors;
    ArrayList<Review> reviews;
    String aboutMe;
    String linkedIn;
    //TODO FIX THIS
    //temporary for backlog user story
    ArrayList<Course> backLog;

    public Student(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        academicAdvisors = new ArrayList<>();
        reviews = new ArrayList<>();
        this.GPA = 0;
        backLog = new ArrayList<>();
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public ArrayList<Course> getBackLog() {
        return backLog;
    }

    public void setBackLog(ArrayList<Course> backLog) {
        this.backLog = backLog;
    }

    public void removeCourseBacklog(String id) {
        for (int i = 0; i < backLog.size(); i++) {
            if (backLog.get(i).getCourseID().equals(id)) {
                backLog.remove(i);
                return;
            }
        }
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
                ", planOfStudy=" + planOfStudy +
                ", academicAdvisors=" + academicAdvisors +
                ", reviews=" + reviews +
                '}';
    }
}