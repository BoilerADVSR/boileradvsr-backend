package com.boileradvsr.backend.models;
import java.util.ArrayList;

public class Requirement {
    String requirementName;
    ArrayList<Course> courses;

    public Requirement(String requirementName) {
        this.requirementName = requirementName;
    }

    public void addCourse(Course course) {
        courses.add(course);
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