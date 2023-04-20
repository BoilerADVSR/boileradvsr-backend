package com.boileradvsr.backend.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;

@Document(collection="requirements")
public class Requirement {
    @Id
    String name;
    ArrayList<Course> courses;
    Type type;

    public enum Type {
        CORE,
        ELECTIVE
    }

    public Requirement(String requirementName, Type type) {
        this.type = type;
        this.name = requirementName;
        courses = new ArrayList<>();
    }
    public Requirement(String requirementName, Type type, Course course) {
        this.name = requirementName;
        this.type = type;
        courses = new ArrayList<>();
        courses.add(course);

    }

    public Requirement(String requirementName, Type type, ArrayList<Course> courses) {
        this.name = requirementName;
        this.type = type;
        this.courses = courses;

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

    public ArrayList<String> getCourseIDs() {
        ArrayList<String> courseIds = new ArrayList<>();
        for (Course course : courses) courseIds.add(course.getCourseID());
        return courseIds;
    }

    public String getName() {
        return name;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}