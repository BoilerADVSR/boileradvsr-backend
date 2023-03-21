package com.boileradvsr.backend.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Document(collection = "students")
public class Student implements UserDetails {
    String firstName;
    String lastName;
    @Id
    String email;
    String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Transient
    Semester graduationSemester;
    double GPA;
    @Transient
    PlanOfStudy planOfStudy;
    @Transient
    ArrayList<Advisor> academicAdvisors;
    @Transient
    ArrayList<Review> reviews;

    public Student(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        academicAdvisors = new ArrayList<>();
        reviews = new ArrayList<>();
        this.GPA = 0;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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