package com.boileradvsr.backend.models;

import java.util.ArrayList;

public class Suggest {


    public static ArrayList<String> suggestASemester(Student student, DegreeGraph graph, ArrayList<Degree> concentrations,ArrayList<Course> availableElectives, String sort, int level) {
        ArrayList<Course> coursesTaken = student.getPlanOfStudy().listCoursesTaken();
        ArrayList<String> suggestedCore = graph.getNextEligibleClassesController(coursesTaken, concentrations);
        if (sort.equals("rating")) {
            rankByRating(availableElectives);
        } else {
            rankByAvgGPA(availableElectives);
        }
        if (level != -1) {
            for (int i = 0; i < availableElectives.size(); i++) {
                if (Character.getNumericValue(availableElectives.get(i).getCourseIdNumber().charAt(0)) != level) {
                    availableElectives.remove(i);
                    i--;
                }
            }
        }
        ArrayList<String> suggestedSemester = new ArrayList<>();
        if (suggestedCore.size() > 2) {
            suggestedSemester.add(suggestedCore.get(0));
            suggestedSemester.add(suggestedCore.get(1));
        } else {
            suggestedSemester.addAll(suggestedCore);
        }

        if (availableElectives.size() > 3 ) {
            suggestedSemester.add(availableElectives.get(0).getCourseID());
            suggestedSemester.add(availableElectives.get(1).getCourseID());
            suggestedSemester.add(availableElectives.get(2).getCourseID());
        } else {
            for (Course course : availableElectives) {
                suggestedSemester.add(course.getCourseID());
            }
        }

        return suggestedSemester;
    }

    public static ArrayList<Course> rankByRating(ArrayList<Course> courses) {
        int n = courses.size();
        Course temp = null;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (courses.get(j - 1).getAverageRating() < courses.get(j).getAverageRating()) {
                    //swap elements
                    temp = courses.get(j - 1);
                    courses.set(j - 1, courses.get(j));
                    courses.set(j, temp);
                }

            }
        }
        return courses;
    }

    public static ArrayList<Course> rankByAvgGPA(ArrayList<Course> courses) {
        int n = courses.size();
        Course temp = null;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (courses.get(j - 1).getAverageGPA() < courses.get(j).getAverageGPA()) {
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
