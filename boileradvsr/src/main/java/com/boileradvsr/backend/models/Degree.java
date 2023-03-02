package com.boileradvsr.backend.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="degrees")
public class Degree {

    public enum DEGREETYPE {
        MAJOR,
        MINOR,
        CONCENTRATION,
        CERTIFICATE
    }

    DEGREETYPE degreeType;
    @Id
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
    public DEGREETYPE getDegreeType() {
        return degreeType;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public String getCollege() {
        return college;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public ArrayList<String> getSpecializationsAvailable() {
        return specializationsAvailable;
    }

    public ArrayList<String> getSpecializationsSelected() {
        return specializationsSelected;
    }

    public void addSpecialization(String specialization) {
        specializationsSelected.add(specialization);
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    public void setDegreeType(DEGREETYPE degreeType) {
        this.degreeType = degreeType;
    }

    public ArrayList<String> getAvailableSpecializations() {
        return(specializationsAvailable);
    }
}