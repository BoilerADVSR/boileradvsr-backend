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
}