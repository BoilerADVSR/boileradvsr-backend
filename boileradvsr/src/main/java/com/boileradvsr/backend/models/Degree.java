package com.boileradvsr.backend.models;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="degrees")
public class Degree {

    enum DEGREETYPE {
        MAJOR,
        MINOR,
        CONCENTRATION,
        CERTIFICATE
    }

    DEGREETYPE degreeType;
    String degreeTitle;
    String college;
    String department;
    ArrayList<Requirement> requirements;
    ArrayList<String> specializationsAvailable;
    ArrayList<String> specializationsSelected;
    
    public Degree(DEGREETYPE degreeType, String degreeTitle, String college, String department) {
        this.degreeType = degreeType;
        this.degreeTitle = degreeTitle;
        this.college = college;
        this.department = department;
        specializationsSelected = new ArrayList<String>();
        specializationsAvailable = new ArrayList<String>();
        requirements = new ArrayList<Requirement>();



    }

    public ArrayList<String> getAvailableSpecializations() {
        return(specializationsAvailable);
    }
}