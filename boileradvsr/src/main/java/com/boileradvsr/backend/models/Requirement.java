package com.boileradvsr.backend.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="requirements")
public class Requirement {
    @Id
    String name;
    ArrayList<Course> courses;

    public Requirement(String requirementName) {
        this.name = requirementName;
        courses = new ArrayList<>();
    }
    public Requirement(String requirementName, Course course) {
        this.name = requirementName;
        courses = new ArrayList<>();
        courses.add(course);

    }

    public Requirement() {}


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

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }
}