package com.backend.boileradvsr.boileradvsr;
import java.util.ArrayList;

public class Degree {

    enum DegreeType {
        MAJOR,
        MINOR,
        CONCENTRATION,
        CERTIFICATE
    }

    DegreeType degreeType;
    String degreeTitle;
    String college;
    String department;
    ArrayList<Requirement> requirements;
    ArrayList<String> specializationsAvailable;
    ArrayList<String> specializationsSelected;
    
    public Degree(DegreeType degreeType, String degreeTitle, String college, String department) {
        this.degreeType = degreeType;
        this.degreeTitle = degreeTitle;
        this.college = college;
        this.department = department;
    }
}