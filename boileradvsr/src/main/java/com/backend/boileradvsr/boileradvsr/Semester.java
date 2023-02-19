package com.backend.boileradvsr.boileradvsr;
import java.util.ArrayList;

public class Semester {
    enum Season {
        FALL,
        SPRING,
        SUMMER
    }

    int year;
    Season season;
    ArrayList<Course> courses;

    public Semester(int year, Season season, ArrayList<Course> courses) {
        this.year = year;
        this.season = season;
        this.courses = courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
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

}