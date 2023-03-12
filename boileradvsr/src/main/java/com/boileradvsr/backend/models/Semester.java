package com.boileradvsr.backend.models;
import java.util.ArrayList;

public class Semester {
    public enum Season {
        FALL,
        SPRING,
        SUMMER
    }

    int year;
    Season season;
    ArrayList<Course> courses;
    double gpa;

    public Semester(int year, Season season, ArrayList<Course> courses) {
        this.year = year;
        this.season = season;
        this.courses = courses;
        calculateGPA();
    }
    public Semester(int year, Season season) {
        this.year = year;
        this.season = season;
        courses = new ArrayList<>();
        gpa = 0.0;
    }

    public Semester() {}


    public void addCourse(Course course) {
        courses.add(course);
        calculateGPA();
    }

    public void calculateGPA() {
        double gpas = 0.0;
        for (Course course : courses) gpas += course.getGrade().getGpaValue();
        gpas /= courses.size();
        gpa = gpas;
    }

    public ArrayList<Course> getCourses() {
        return(courses);
    }

    public boolean removeCourse(Course course) {
        for (int index = 0; index < courses.size(); index++) {
            if (course.equals(courses.get(index))) {
                courses.remove(index);
                return true;
            }
        }
        return false;
    }

    public int getYear() {
        return year;
    }

    public Season getSeason() {
        return season;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "year=" + year +
                ", season=" + season +
                ", courses=" + courses +
                '}';
    }
}