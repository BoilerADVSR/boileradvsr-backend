package com.backend.boileradvsr.boileradvsr;
import java.util.ArrayList;

public class Requirement {
    String requirementName;
    ArrayList<Course> courses;

    public Requirement(String requirementName) {
        this.requirementName = requirementName;
    }
}