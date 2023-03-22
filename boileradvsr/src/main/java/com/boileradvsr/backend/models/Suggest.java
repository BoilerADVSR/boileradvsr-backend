package com.boileradvsr.backend.models;

import com.boileradvsr.backend.controllers.CourseController;
import com.boileradvsr.backend.controllers.StudentController;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Suggest {


    public static ArrayList<Course> suggestASemester(Student student, DegreeGraph graph, ArrayList<Course> availableCourses) {
        ArrayList<Course> coursesTaken = student.getPlanOfStudy().getCoursesTaken();
        ArrayList<String> suggestedNames = graph.getNextEligibleClassesController(coursesTaken, new ArrayList<>());


        rank(availableCourses);
        return null;
    }

    public static ArrayList<Course> rank(ArrayList<Course> courses) {
        int n = courses.size();
        Course temp = null;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (courses.get(j - 1).getAverageRating() > courses.get(j).getAverageRating()) {
                    //swap elements
                    temp = courses.get(j - 1);
                    courses.set(j - 1, courses.get(j));
                    courses.set(j, temp);
                }

            }
        }
        return courses;
    }

}
